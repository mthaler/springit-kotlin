package com.mthaler.springit.controller

import com.mthaler.springit.domain.Link
import com.mthaler.springit.repository.LinkRepository
import com.mthaler.springit.repository.VoteRepository
import org.springframework.web.bind.annotation.RestController
import com.mthaler.springit.domain.Vote
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@RestController
class VoteController(val voteRepository: VoteRepository, val linkRepository: LinkRepository) {

    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    fun vote(@PathVariable linkID: Long?, @PathVariable direction: Short, @PathVariable voteCount: Int): Int {
        val optionalLink: Optional<Link> = linkRepository.findById(linkID!!)
        if (optionalLink.isPresent()) {
            val link: Link = optionalLink.get()
            val vote = Vote(direction, link)
            voteRepository.save(vote)
            val updatedVoteCount = voteCount + direction
            link.voteCount = updatedVoteCount
            linkRepository.save<Link>(link)
            return updatedVoteCount
        }
        return voteCount
    }
}