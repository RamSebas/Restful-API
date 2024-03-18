package com.asrc.learningspringboot.Model

import java.util.UUID

class User (
    private val userUid: UUID = TODO(),
    private val firstName: String = "",
    private val lastName: String,
    private val gender: Gender,
    private val age: Int,
    private val email: String,

) {
    fun getUserUid(): UUID {
        return userUid
    }

    fun getFirstName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

    fun getGender(): Gender {
        return gender
    }

    fun getAge(): Int {
        return age
    }

    fun getEmail(): String {
        return email
    }
    override fun toString(): String {
        return "User(userUid=$userUid, firstName='$firstName', lastName='$lastName', gender=$gender, age=$age, email='$email')"
    }
    enum class Gender {
        MALE,
        FEMALE
    }
}