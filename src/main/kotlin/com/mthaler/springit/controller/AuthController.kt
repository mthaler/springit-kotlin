package com.mthaler.springit.controller

import com.mthaler.springit.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController(val userService: UserService) {

    @GetMapping("/login")
    fun login(): String {
        return "auth/login"
    }

    @GetMapping("/profile")
    fun profile(): String {
        return "auth/profile"
    }

    @GetMapping("/register")
    fun register(): String {
        return "auth/register"
    }
}