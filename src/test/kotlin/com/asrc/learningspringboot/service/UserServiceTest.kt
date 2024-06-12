package com.asrc.learningspringboot.service

import com.asrc.learningspringboot.dataBase.UserRepository
import com.asrc.learningspringboot.model.Gender
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
    private lateinit var userRepository: UserRepository

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userService = UserService(userRepository)
    }

    @Test
    fun shouldGetAllUsers() {
        val alexanderUserUid = UUID.randomUUID()
        val alexander = User(alexanderUserUid, "Alexander", "Ramirez", gender = Gender.MALE, 27, "prueba@gmail.com")
        val listaUsuarios = mutableListOf<User>()
        listaUsuarios.add(alexander)
        given(userRepository.findAll()).willReturn(listaUsuarios)
        val users = userRepository.findAll()
        val userList = ArrayList(users)
        assertThat(userList).hasSize(1)
        val user = userList[0]
        assertThat(user.age).isEqualTo(27)
        assertThat(user.firstName).isEqualTo("Alexander")
        assertThat(user.lastName).isEqualTo("Ramirez")
        assertThat(user.gender).isEqualTo(Gender.MALE)
        assertThat(user.email).isEqualTo("prueba@gmail.com")
        assertThat(user.userUid).isNotNull()
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