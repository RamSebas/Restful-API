package com.asrc.learningspringboot.dao

import com.asrc.learningspringboot.model.User
import java.util.*
import kotlin.collections.ArrayList


interface UserDao {
    fun selectAllUsers(): MutableCollection<User>
    fun selectUserByUserUid(userUid: UUID): User?
    fun updateUser(user: User): Int
    fun deleteUserByUserUid(userUid: UUID): Int
    fun insertUser(userUid: UUID, user: User): Int
}