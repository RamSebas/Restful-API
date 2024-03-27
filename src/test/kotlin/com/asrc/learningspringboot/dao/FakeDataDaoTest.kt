package com.asrc.learningspringboot.dao

import com.asrc.learningspringboot.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import java.util.*
import kotlin.collections.ArrayList
import org.springframework.util.ClassUtils.isPresent


class FakeDataDaoTest {
    private lateinit var fakeDataDao: FakeDataDao
    @BeforeEach
    fun setUp() {
        fakeDataDao = FakeDataDao()
    }

    @Test
    fun shouldSelectAllUsers() {
        val users = fakeDataDao.selectAllUsers()
        val userList = ArrayList(users)
        assertThat(users).hasSize(1)
        val user = userList[0]
        assertThat(user.getAge()).isEqualTo(22)
        assertThat(user.getFirstName()).isEqualTo("Joe")
        assertThat(user.getLastName()).isEqualTo("Jones")
        assertThat(user.getGender()).isEqualTo("MALE")
        assertThat(user.getEmail()).isEqualTo("joejones@gmail.com")
        assertThat(user.getUserUid()).isNotNull()
        println("-El test shouldSelectAllUsers funciona correctamente-")
    }

    @Test
    fun shouldSelectUserByUserUid() {
        val alexanderUserUid = UUID.randomUUID()
        val alexander = User(alexanderUserUid, "Alexander", "Ramirez", User.Gender.MALE, 27, "prueba@gmail.com")
        fakeDataDao.insertUser(alexanderUserUid, alexander)
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2)

        val alexanderOptional = fakeDataDao.selectUserByUserUid(alexanderUserUid)
        assertThat(isPresent(alexanderOptional.toString(), null))
        assertThat(alexanderOptional).isEqualToComparingFieldByField(alexanderOptional)
        println("-El test shouldSelectUserByUserUid funciona correctamente-")
    }

    @Test
    fun shouldUpdateUser() {
        val users = fakeDataDao.selectAllUsers()
        val userList = ArrayList(users)
        val joeUserUid = userList[0].getUserUid()
        val newJoeUserUid = User(joeUserUid, "Anna", "Montana", User.Gender.FEMALE, 30, "annamontana@gmail.com")
        fakeDataDao.updateUser(newJoeUserUid)
        println(userList)
        val user = fakeDataDao.selectUserByUserUid(joeUserUid)
        assertThat(users).hasSize(1)
        assertThat(user).isEqualToComparingFieldByField(newJoeUserUid)
        println("Update correct")


    }

    @Test
    fun shouldDeleteUserByUserUid() {
        val users = fakeDataDao.selectAllUsers()
        val userList =ArrayList(users)
        val joeUserUid = userList[0].getUserUid()
        fakeDataDao.deleteUserByUserUid(joeUserUid)
        assertThat(users).isEmpty()
        assertThat(fakeDataDao.selectUserByUserUid(joeUserUid)).isNotIn(fakeDataDao)
        println("Usuario eliminado")
        println("Cantidad de usuarios es ${fakeDataDao.selectAllUsers().size}")

    }

    @Test
    fun shouldInsertUser() {
        val userUid = UUID.randomUUID()
        val newJoeUserUid = User(userUid, "Anna", "Montana", User.Gender.FEMALE, 30, "annamontana@gmail.com")
        fakeDataDao.insertUser(userUid, newJoeUserUid)
        val users = fakeDataDao.selectAllUsers()
        val userList =ArrayList(users)
        assertThat(userList).hasSize(2)
        assertThat(fakeDataDao.selectUserByUserUid(userUid)).isEqualToComparingFieldByField(newJoeUserUid)
        println(userList)
    }
}


