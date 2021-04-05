package com.mthaler.springit.controller

import com.mthaler.springit.domain.Link
import com.mthaler.springit.repository.LinkRepository
import com.mthaler.springit.repository.VoteRepository
import org.springframework.web.bind.annotation.RestController
import com.mthaler.springit.domain.Vote
import com.mthaler.springit.service.LinkService
import com.mthaler.springit.service.VoteService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@RestController
class VoteController(val voteService: VoteService, val linkService: LinkService) {

    @Secured(value = ["ROLE_USER"])
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    fun vote(@PathVariable linkID: Long?, @PathVariable direction: Short, @PathVariable voteCount: Int): Int {
        val optionalLink: Optional<Link> = linkService.findById(linkID!!)
        if (optionalLink.isPresent()) {
            val link: Link = optionalLink.get()
            val vote = Vote(direction, link)
            voteService.save(vote)
            val updatedVoteCount = voteCount + direction
            link.voteCount = updatedVoteCount
            linkService.save(link)
            return updatedVoteCount
        }
        return voteCount
    }
}