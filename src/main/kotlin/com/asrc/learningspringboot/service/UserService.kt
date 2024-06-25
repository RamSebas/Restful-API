package com.asrc.learningspringboot.service

import com.asrc.learningspringboot.dataBase.UserRepository
import com.asrc.learningspringboot.model.Gender
import com.asrc.learningspringboot.model.User
import com.asrc.learningspringboot.resources.UserResource.ErrorMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors




@Service
class UserService(@Autowired private val userRepository: UserRepository) {

//    lateinit var userRepository: UserRepository
//    @Autowired
//    fun userService(userRepository: UserRepository) {
//        this.userRepository = userRepository
//    }

    fun getAllUsers(gender: String?, firstName: String?): Collection<User> {
        val users = userRepository.findAll()
        var filteredUsers = users
        if (gender != null) {
            try {
                val theGender: Gender = Gender.valueOf(gender.toUpperCase())
                filteredUsers = filteredUsers.stream()
                    .filter { user: User -> user.gender == theGender }
                    .collect(Collectors.toList())
            } catch (e: Exception) {
                throw IllegalStateException("Invalid gender", e)
            }
        }
        if (firstName != null) {
            filteredUsers = filteredUsers.stream()
                .filter { user: User -> user.firstName == firstName }
                .collect(Collectors.toList())
        }
        return filteredUsers
    }


    fun getUser(userUid: UUID): User? {
        return userRepository.findById(userUid).orElse(null)
    }

    fun updateUser(user: User): Any {
        val currentUser = user.userUid?.let { getUser(it) }
        if (currentUser != null) {
            userRepository.save(user)
            return ResponseEntity.status(HttpStatus.OK).body("User ${currentUser.userUid} was updated")
        } else return -1
    }

    fun removeUser(userUid: UUID): Any {
        val currentUser = getUser(userUid)
        if (currentUser != null) {
            userRepository.deleteById(userUid)
            return ResponseEntity.status(HttpStatus.OK).body("User $userUid was removed")
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage("User $userUid was not found"))
    }

    fun insertUser(user: User): ResponseEntity<String> {
        val userUid = user.userUid?: UUID.randomUUID()
        if (user.firstName == "" || user.lastName == "" || user.age <= 0 || user.email == "") {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        userRepository.save(user)
        return ResponseEntity.status(HttpStatus.CREATED).body("User $userUid was inserted")
    }

}