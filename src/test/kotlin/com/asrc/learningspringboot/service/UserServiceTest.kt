package com.asrc.learningspringboot.service

import com.asrc.learningspringboot.dao.FakeDataDao
import com.asrc.learningspringboot.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.assertj.core.api.Assertions.assertThat
import org.mockito.BDDMockito.given
import java.util.*
import kotlin.collections.ArrayList


class UserServiceTest {

    @Mock
    private lateinit var fakeDataDao: FakeDataDao

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userService = UserService(fakeDataDao)
    }

    @Test
    fun shouldGetAllUsers() {
        val alexanderUserUid = UUID.randomUUID()
        val alexander = User(alexanderUserUid, "Alexander", "Ramirez", User.Gender.MALE, 27, "prueba@gmail.com")
        val listaUsuarios = mutableListOf<User>()
        listaUsuarios.add(alexander)
        given(fakeDataDao.selectAllUsers()).willReturn(listaUsuarios)
        val users = fakeDataDao.selectAllUsers()
        val userList = ArrayList(users)
        assertThat(userList).hasSize(1)
        val user = userList[0]
        assertThat(user.getAge()).isEqualTo(27)
        assertThat(user.getFirstName()).isEqualTo("Alexander")
        assertThat(user.getLastName()).isEqualTo("Ramirez")
        assertThat(user.getGender()).isEqualTo("MALE")
        assertThat(user.getEmail()).isEqualTo("prueba@gmail.com")
        assertThat(user.getUserUid()).isNotNull()
        println("Prueba exitosa")
    }


        @Test
    fun getUser() {
    }

    @Test
    fun updateUser() {
    }

    @Test
    fun removeUser() {
    }

    @Test
    fun insertUser() {
    }
}