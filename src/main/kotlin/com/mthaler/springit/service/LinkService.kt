package com.mthaler.springit.service

import com.mthaler.springit.domain.Link
import com.mthaler.springit.repository.LinkRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class LinkService(val linkRepository: LinkRepository) {

    fun findAll(): List<Link> {
        return linkRepository.findAll()
    }

    fun findById(id: Long): Optional<Link> {
        return linkRepository.findById(id)
    }

    fun save(link: Link): Link {
        return linkRepository.save(link)
    }
}