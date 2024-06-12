package com.asrc.learningspringboot.service

import com.asrc.learningspringboot.dataBase.UserRepository
import com.asrc.learningspringboot.model.Gender
import com.asrc.learningspringboot.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.collections.ArrayList

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
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
    fun `getUser returns the correct user`() {
        val userUid = UUID.randomUUID()
        val user = User(userUid, "Test", "User", gender = Gender.MALE, 22, "test.user@example.com")
        val userList = mutableListOf<User>()
        userList.add(user)
        given(userRepository.findById(userUid)).willReturn(Optional.of(user))

        val returnedUser = userService.getUser(userUid)

        assertThat(returnedUser).isNotNull
        assertThat(returnedUser?.userUid).isEqualTo(user.userUid)
        assertThat(returnedUser?.firstName).isEqualTo(user.firstName)
        assertThat(returnedUser?.lastName).isEqualTo(user.lastName)
        assertThat(returnedUser?.gender).isEqualTo(user.gender)
        assertThat(returnedUser?.age).isEqualTo(user.age)
        assertThat(returnedUser?.email).isEqualTo(user.email)
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