package com.mthaler.springit.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @RequestMapping("/")
    fun home(): String {
        return "Hello, Spring Boot 2!"
    }
}