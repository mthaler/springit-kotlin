package com.mthaler.springit.controller

import com.mthaler.springit.repository.LinkRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LinkController(val linkRepository: LinkRepository) {

    @GetMapping("/")
    fun list(model: Model): String {
        model.addAttribute("links", linkRepository.findAll())
        return "link/list"
    }
}