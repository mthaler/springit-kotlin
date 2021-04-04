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
class Comment(@Id @GeneratedValue val id: Long,
                   val body: String,
                   @ManyToOne val link: Link?,
                   @CreatedBy val createdBy: String,
                   @CreatedDate val creationDate: LocalDateTime,
                   @LastModifiedBy val lastModifiedBy: String,
                   @LastModifiedDate val lastModifiedDate: LocalDateTime)