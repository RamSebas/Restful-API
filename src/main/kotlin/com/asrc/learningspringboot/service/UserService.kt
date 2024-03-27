package com.asrc.learningspringboot.service

import com.asrc.learningspringboot.dao.UserDao
import com.asrc.learningspringboot.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService {

    lateinit var userDao: UserDao
    @Autowired
    fun UserService(userDao: UserDao) {
        this.userDao = userDao
    }

    fun getAllUsers(): MutableCollection<User> {
        return userDao.selectAllUsers()
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