package com.mthaler.springit.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class FooController {

    @GetMapping("/foo")
    fun foo(model: Model): String {
        model.addAttribute("pageTitle", "This is the page FOO")
        return "foo"
    }
}