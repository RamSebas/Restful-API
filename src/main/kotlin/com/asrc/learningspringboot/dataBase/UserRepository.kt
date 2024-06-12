package com.asrc.learningspringboot.dataBase

import com.asrc.learningspringboot.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID>{

}