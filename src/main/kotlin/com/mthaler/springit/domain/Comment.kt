package com.mthaler.springit.domain

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Comment(
    var body: String,
    @ManyToOne var link: Link?,
    @Id @GeneratedValue var id: Long? = null,
    @CreatedBy var createdBy: String? = null,
    @CreatedDate var creationDate: LocalDateTime? = null,
    @LastModifiedBy var lastModifiedBy: String? = null,
    @LastModifiedDate var lastModifiedDate: LocalDateTime? = null
)