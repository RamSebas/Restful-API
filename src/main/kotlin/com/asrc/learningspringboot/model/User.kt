package com.asrc.learningspringboot.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Table(name = "user_api")
@Entity
data class User(
    @Id
    @JsonProperty("userUid")
    var userUid: UUID? = UUID.randomUUID(),
    @JsonProperty("firstName")
    var firstName: String = "",
    @JsonProperty("lastName")
    var lastName: String = "",
    @JsonProperty("gender")
    @Enumerated(EnumType.STRING)
    var gender: Gender = Gender.MALE,
    @JsonProperty("age")
    var age: Long = 0L,
    @JsonProperty("email")
    var email: String = "",
)

enum class Gender {
    MALE,
    FEMALE
}