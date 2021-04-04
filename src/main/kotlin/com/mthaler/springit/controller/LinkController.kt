package com.mthaler.springit.controller

import com.mthaler.springit.domain.Link
import com.mthaler.springit.repository.LinkRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/links")
class LinkController(val linkRepository: LinkRepository) {

    @GetMapping("/")
    fun list(): List<Link> = linkRepository.findAll()

    // CRUD

    @PostMapping("/create")
    fun create(@ModelAttribute link: Link): Link = linkRepository.save(link)

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): Optional<Link> = linkRepository.findById(id)

    @PutMapping("/{id}")
    fun update(@ModelAttribute link: Link): Link = linkRepository.save(link)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = linkRepository.deleteById(id)
}