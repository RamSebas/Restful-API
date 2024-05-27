package com.asrc.learningspringboot.service

import com.asrc.learningspringboot.dao.FakeDataDao
import com.asrc.learningspringboot.dao.UserDao
import com.asrc.learningspringboot.model.User
import com.asrc.learningspringboot.model.User.Gender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(fakeDataDao: FakeDataDao) {

    lateinit var userDao: UserDao
    @Autowired
    fun UserService(userDao: UserDao) {
        this.userDao = userDao
    }

    fun getAllUsers(gender: String?): Collection<User> {
        val users = userDao.selectAllUsers()
        if (gender == null) {
            return users
        }
        return try {
            val theGender = Gender.valueOf(gender.toUpperCase())
            users.filter { it.gender == theGender }
        } catch (e: Exception) {
            throw IllegalStateException("Invalid gender", e)
        }
    }


    fun getUser(userUid: UUID): User? {
        return userDao.selectUserByUserUid(userUid)
    }

    fun updateUser(user: User): Int {
        val currentUser = getUser(user.getUserUid())
        if (currentUser != null) {
            userDao.updateUser(user)
            return 1
        } else return -1
    }

    fun removeUser(userUid: UUID): Int {
        val currentUser = getUser(userUid)
        if (currentUser != null) {
            userDao.deleteUserByUserUid(userUid)
            return 1
        } else return -1
    }

    fun insertUser(user: User): Int {
        return userDao.insertUser(UUID.randomUUID(), user)
    }

}