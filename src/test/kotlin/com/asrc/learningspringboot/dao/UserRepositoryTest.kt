package com.asrc.learningspringboot.dao

import com.asrc.learningspringboot.dataBase.UserRepository
import com.asrc.learningspringboot.model.Gender
import com.asrc.learningspringboot.model.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import java.util.*



@ExtendWith(MockitoExtension::class)
@SpringBootTest
class UserRepositoryTest() {
    @Mock
    lateinit var userRepository: UserRepository
    @Test
    fun finById() {
        val user = User(UUID.randomUUID(), "Test", "User", gender = Gender.MALE, 22, "ar@gmail.com")

        Mockito.`when`(user.userUid?.let { userRepository.findById(it) }).thenReturn(Optional.of(user))

        user.userUid?.let {
            userRepository.findById(it)?.ifPresent { userFromDb ->
                Assertions.assertEquals(user.userUid, userFromDb.userUid)
                Assertions.assertEquals(user.firstName, userFromDb.firstName)
                Assertions.assertEquals(user.lastName, userFromDb.lastName)
                Assertions.assertEquals(user.email, userFromDb.email)
                println("Prueba exitosa")
            }
        }
    }
}
