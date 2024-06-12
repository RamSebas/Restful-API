package com.asrc.learningspringboot.service

import com.asrc.learningspringboot.dataBase.UserRepository
import com.asrc.learningspringboot.model.Gender
import com.asrc.learningspringboot.model.User
import org.springframework.beans.factory.annotation.Autowired
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

    fun getAllUsers(gender: String?): Collection<User> {
        val users = userRepository.findAll()
        if (gender == null) {
            return users
        }
            try {
            val theGender: Gender = Gender.valueOf(gender.toUpperCase())
                return users.stream()
                    .filter { user: User -> user.gender == theGender }
                    .collect(Collectors.toList())
        } catch (e: Exception) {
            throw IllegalStateException("Invalid gender", e)
        }
    }


    fun getUser(userUid: UUID): User? {
        return userRepository.findById(userUid).orElse(null)
    }

    fun updateUser(user: User): Int {
        val currentUser = user.userUid?.let { getUser(it) }
        if (currentUser != null) {
            userRepository.save(user)
            return 1
        } else return -1
    }

    fun removeUser(userUid: UUID): Int {
        val currentUser = getUser(userUid)
        if (currentUser != null) {
            userRepository.deleteById(userUid)
            return 1
        } else return -1
    }

    fun insertUser(user: User): Int {
        val userUid = user.userUid?: UUID.randomUUID()
        userRepository.save(user)
        return 1
    }

}