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
data class Vote(@Id @GeneratedValue val id: Long,
                val vote: Int,
                @CreatedBy val createdBy: String,
                @CreatedDate val creationDate: LocalDateTime,
                @LastModifiedBy val lastModifiedBy: String,
                @LastModifiedDate val lastModifiedDate: LocalDateTime
)
