package com.asrc.learningspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class LearningSpringBootApplication

fun main(args: Array<String>) {
	runApplication<LearningSpringBootApplication>(*args)
}
