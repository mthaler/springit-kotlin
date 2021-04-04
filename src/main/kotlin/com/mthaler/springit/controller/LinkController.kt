package com.mthaler.springit.controller

import com.mthaler.springit.domain.Link
import com.mthaler.springit.repository.LinkRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

@Controller
class LinkController(val linkRepository: LinkRepository) {

    @GetMapping("/")
    fun list(model: Model): String {
        model.addAttribute("links", linkRepository.findAll())
        return "link/list"
    }

    @GetMapping("/link/{id}")
    fun read(@PathVariable id: Long?, model: Model): String? {
        val link: Optional<Link> = linkRepository.findById(id!!)
        return if (link.isPresent()) {
            model.addAttribute("link", link.get())
            model.addAttribute("success", model.containsAttribute("success"))
            "link/view"
        } else {
            "redirect:/"
        }
    }
}