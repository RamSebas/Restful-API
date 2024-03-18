package com.asrc.learningspringboot.DAO

import com.asrc.learningspringboot.Model.User
import java.util.*
import kotlin.collections.ArrayList


interface UserDao {
    fun selectAllUsers(): ArrayList<MutableCollection<User>>
    fun selectUserByUserUid(userUid: UUID): User?
    fun updateUser(user: User): Int
    fun deleteUserByUserUid(userUid: UUID): Int
    fun insertUser(userUid: UUID, user: User): Int
}