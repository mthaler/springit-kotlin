package com.mthaler.springit.controller

import com.mthaler.springit.domain.User
import com.mthaler.springit.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
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
    fun register(model: Model): String {
        model.addAttribute("user", User())
        model.addAttribute("success", false)
        return "auth/register"
    }
}