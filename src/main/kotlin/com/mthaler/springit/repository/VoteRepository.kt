package com.mthaler.springit.repository

import com.mthaler.springit.domain.Vote
import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository: JpaRepository<Vote, Long>