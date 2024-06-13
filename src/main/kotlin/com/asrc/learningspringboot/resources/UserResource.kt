package com.asrc.learningspringboot.resources

import com.asrc.learningspringboot.model.User
import com.asrc.learningspringboot.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/v1/users")

class UserResource @Autowired constructor(private val userService: UserService){

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun fetchUsers(@RequestParam("gender") gender: String?, @RequestParam("firstName", required = false) firstName: String?): Collection<User> {
        return userService.getAllUsers(gender, firstName)
    }

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE], path = ["{userUid}"])
    fun fetchUser (@PathVariable("userUid") userUid: UUID): Any? {
        var userOptional: User? = userService.getUser(userUid)
        if (userOptional != null) {
                return userService.getUser(userUid)
                    }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage("User $userUid was not found"))
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun insertNewUser(@RequestBody user: User): ResponseEntity<String> {
        val result = userService.insertUser(user)
        return result
    }
    @DeleteMapping("{userUid}")
    fun deleteUsers(@PathVariable("userUid") userUid: UUID): Any {
        val result = userService.removeUser(userUid)
        return result
    }

    @RequestMapping(method = [RequestMethod.PUT], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateUser(@RequestBody user: User): Any {
        val result = userService.updateUser(user)
        return result
    }

    class ErrorMessage(private var errorMessage: String) {
        fun errorMessage(errorMessage: String){
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

private fun intResponseEntity(result: Any): ResponseEntity<Int> {
    if (result == 1) {
        return ResponseEntity.ok().build()
    }
    return ResponseEntity.badRequest().build()
}

