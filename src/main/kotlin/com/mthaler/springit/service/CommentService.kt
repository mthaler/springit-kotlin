package com.mthaler.springit.service

import com.mthaler.springit.domain.Comment
import com.mthaler.springit.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(val commentRepository: CommentRepository) {

    fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }
}