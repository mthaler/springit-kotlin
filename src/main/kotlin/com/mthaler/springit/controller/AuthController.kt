package com.mthaler.springit.controller

import com.mthaler.springit.domain.User
import com.mthaler.springit.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

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
        return "auth/register"
    }

    @PostMapping("/register")
    fun registerNewUser(
        @Valid user: User,
        bindingResult: BindingResult,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        return if (bindingResult.hasErrors()) {
            // show validation errors
            logger.info("Validation errors were found while registering a new user")
            model.addAttribute("user", user)
            model.addAttribute("validationErrors", bindingResult.allErrors)
            "auth/register"
        } else {
            // Register new user
            val newUser = userService.register(user)
            redirectAttributes
                .addAttribute("id", newUser.id)
                .addFlashAttribute("success", true)
            "redirect:/register"
        }
    }

    @GetMapping("/activate/{email}/{activationCode}")
    fun activate(@PathVariable email: String, @PathVariable activationCode: String): String {
        val user: Optional<User> = userService.findByEmailAndActivationCode(email, activationCode)
        if (user.isPresent()) {
            val newUser: User = user.get()
            newUser.enabled = true
            newUser.confirmPassword = newUser.password
            userService.save(newUser)
            userService.sendWelcomeEmail(newUser)
            return "auth/activated"
        }
        return "redirect:/"
    }

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)
    }
}