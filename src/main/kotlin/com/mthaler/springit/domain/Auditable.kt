package com.mthaler.springit.domain

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditable {

    @CreatedBy
    var createdBy: String? = null

    @CreatedDate
    var creationDate: LocalDateTime? = null

    @LastModifiedBy
    var lastModifiedBy: String? = null

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null
}