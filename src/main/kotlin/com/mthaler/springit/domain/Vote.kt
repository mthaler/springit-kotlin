package com.mthaler.springit.domain

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Vote(
    var vote: Int,
    @Id @GeneratedValue var id: Long? = null,
    @CreatedBy var createdBy: String? = null,
    @CreatedDate var creationDate: LocalDateTime? = null,
    @LastModifiedBy var lastModifiedBy: String? = null,
    @LastModifiedDate var lastModifiedDate: LocalDateTime? = null
)
