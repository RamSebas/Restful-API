package com.asrc.learningspringboot.dao

import com.asrc.learningspringboot.dataBase.UserRepository
import com.asrc.learningspringboot.model.Gender
import com.asrc.learningspringboot.model.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class UserRepositoryTest(@Autowired val userRepository: UserRepository) {

    @Test
    fun `when findById then return User`() {
        val user = User(UUID.randomUUID(), "Test", "User", gender = Gender.MALE,22, "ar@gmail.com")
        userRepository.save(user)

        val foundUser = user.userUid?.let { userRepository.findById(it) }

        if (foundUser != null) {
            Assertions.assertTrue(foundUser.isPresent)
        }
        if (foundUser != null) {
            Assertions.assertEquals(user.userUid, foundUser.get().userUid)
        }
        if (foundUser != null) {
            Assertions.assertEquals(user.firstName, foundUser.get().firstName)
        }
        if (foundUser != null) {
            Assertions.assertEquals(user.lastName, foundUser.get().lastName)
        }
        if (foundUser != null) {
            Assertions.assertEquals(user.email, foundUser.get().email)
        }
    }
}

