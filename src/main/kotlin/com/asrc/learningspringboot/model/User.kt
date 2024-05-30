package com.asrc.learningspringboot.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


data class User(
    @JsonProperty("userUid") private var userUid: UUID? = null,
    @JsonProperty("firstName") private var firstName: String = "",
    @JsonProperty("lastName") private var lastName: String,
    @JsonProperty("gender") private var gender: Gender,
    @JsonProperty("age") private var age: Int,
    @JsonProperty("email") private var email: String,
) {
    // Constructor for JSON deserialization
    constructor(
        @JsonProperty("userUid") userUid: UUID?,
        @JsonProperty("firstName") firstName: String?,
        @JsonProperty("lastName") lastName: String?,
        @JsonProperty("gender") gender: Gender?,
        @JsonProperty("age") age: Int?,
        @JsonProperty("email") email: String?,
    ) : this(
        userUid,
        firstName ?: "",
        lastName ?: throw IllegalArgumentException("lastName cannot be null"),
        gender ?: throw IllegalArgumentException("gender cannot be null"),
        age ?: throw IllegalArgumentException("age cannot be null"),
        email ?: throw IllegalArgumentException("email cannot be null")
    )

    fun getUserUid(): UUID? {
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

    fun newUser(userUid: UUID, user: User): User {
        return User(
            userUid,
            user.getFirstName(),
            user.getLastName(),
            user.getGender(),
            user.getAge(),
            user.getEmail()
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