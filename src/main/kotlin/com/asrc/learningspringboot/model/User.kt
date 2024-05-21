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
        userUid: UUID?,
        firstName: String?,
        lastName: String?,
        gender: Gender?,
        age: Int?,
        email: String?,
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

    fun getGender(): String {
        return gender.toString()
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