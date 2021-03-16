package com.mthaler.springit.repository

import com.mthaler.springit.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Long, Comment>