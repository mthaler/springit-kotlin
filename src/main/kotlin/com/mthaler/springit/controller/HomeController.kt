package com.mthaler.springit.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {

    @RequestMapping("/home")
    fun home(model: Model): String {
        model.addAttribute("title", "Hello, Thymeleaf!")
        return "home"
    }
}