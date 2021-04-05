package com.mthaler.springit.service

import com.mthaler.springit.repository.VoteRepository
import org.springframework.stereotype.Service
import com.mthaler.springit.domain.Vote

@Service
class VoteService(val voteRepository: VoteRepository) {

    fun save(vote: Vote): Vote {
        return voteRepository.save(vote)
    }
}