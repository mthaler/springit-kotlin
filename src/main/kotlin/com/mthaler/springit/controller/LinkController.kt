package com.mthaler.springit.controller

import com.mthaler.springit.domain.Comment
import com.mthaler.springit.domain.Link
import com.mthaler.springit.service.CommentService
import com.mthaler.springit.service.LinkService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid;
import org.springframework.security.access.annotation.Secured

@Controller
class LinkController(val linkService: LinkService, val commentService: CommentService) {

    @GetMapping("/")
    fun list(model: Model): String {
        model.addAttribute("links", linkService.findAll())
        return "link/list"
    }

    @GetMapping("/link/{id}")
    fun read(@PathVariable id: Long?, model: Model): String? {
        val link: Optional<Link> = linkService.findById(id!!)
        return if (link.isPresent()) {
            val currentLink = link.get()
            val comment = Comment()
            comment.link = currentLink
            model.addAttribute("comment", comment)
            model.addAttribute("link", currentLink)
            model.addAttribute("success", model.containsAttribute("success"))
            "link/view"
        } else {
            "redirect:/"
        }
    }

    @GetMapping("/link/submit")
    fun newLinkForm(model: Model): String {
        model.addAttribute("link", Link())
        return "link/submit"
    }

    @PostMapping("/link/submit")
    fun createLink(
        @Valid link: Link,
        bindingResult: BindingResult,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        return if (bindingResult.hasErrors()) {
            logger.info("Validation errors were found while submitting a new link.")
            model.addAttribute("link", link)
            "link/submit"
        } else {
            // save our link
            linkService.save(link)
            logger.info("New link was saved successfully")
            redirectAttributes
                .addAttribute("id", link.id)
                .addFlashAttribute("success", true)
            "redirect:/link/{id}"
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/link/comments")
    fun addComment(comment: @Valid Comment, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            logger.info("There was a problem adding a new comment.")
        } else {
            commentService.save(comment)
            logger.info("New comment was saved successfully.")
        }
        return "redirect:/link/" + (comment.link?.id ?: -1L)
    }

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(LinkController::class.java)
    }
}