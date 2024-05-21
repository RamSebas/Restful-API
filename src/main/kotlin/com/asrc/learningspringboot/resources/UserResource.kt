package com.asrc.learningspringboot.resources

import com.asrc.learningspringboot.model.User
import com.asrc.learningspringboot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("api/v1/users")

class UserResource {
    private lateinit var userService: UserService


    @Autowired
    fun UserResource(userService: UserService) {
        this.userService = userService
    }

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fetchUsers(): MutableCollection<User> {
        return userService.getAllUsers()
    }

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE], path = ["{userUid}"])
    fun fetchUser (@PathVariable("userUid") userUid: UUID): ResponseEntity<*>{
        var userOptional: User? = userService.getUser(userUid)
        if (userOptional != null) {
                return ResponseEntity.ok(userOptional.getUserUid())
                    }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage("User $userUid was not found"))
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun insertNewUser(@RequestBody user: User): ResponseEntity<Int>{
        val result = userService.insertUser(user)
        if (result ==1 ){
            return ResponseEntity.ok().build()
        }
        return ResponseEntity.badRequest().build()
    }

    class ErrorMessage(private var errorMessage: String) {
        fun ErrorMessage(errorMessage: String){
            this.errorMessage = errorMessage
        }
        fun getMessage(): String {
            return errorMessage
        }
        fun setMessage(errorMessage: String){
            this.errorMessage = errorMessage
        }
    }
}

