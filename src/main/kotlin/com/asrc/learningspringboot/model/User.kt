package com.asrc.learningspringboot.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


class User (
    private var userUid: UUID,
    private var firstName: String = "",
    private var lastName: String,
    private var gender: Gender,
    private var age: Int,
    private var email: String,
    ) {

    fun User(
        @JsonProperty("userUid") userUid: UUID?,
        @JsonProperty("firstName") firstName: String?,
        @JsonProperty("lastName") lastName: String?,
        @JsonProperty("gender") gender: Gender?,
        @JsonProperty("age") age: Int?,
        @JsonProperty("email") email: String?,
    ) {
        this.userUid = userUid!!
        this.firstName = firstName!!
        this.lastName = lastName!!
        this.gender = gender!!
        this.age = age!!
        this.email = email!!
    }

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

    fun newUser(userUid: UUID?, user: User): User {
        return com.asrc.learningspringboot.model.User(
            userUid!!, user.getFirstName(), user.getLastName(), user.gender,
            user.getAge(), user.getEmail()
        )
    }

    override fun toString(): String {
        return "User(userUid=$userUid, firstName='$firstName', lastName='$lastName', gender=$gender, age=$age, email='$email')"
    }
    enum class Gender {
        MALE,
        FEMALE
    }
}