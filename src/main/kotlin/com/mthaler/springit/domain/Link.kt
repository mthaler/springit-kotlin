package com.mthaler.springit.domain

import com.mthaler.springit.service.BeanUtil
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.net.URI
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import org.ocpsoft.prettytime.PrettyTime
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList


@Entity
class Link(
    var title: String,
    var url: String,
    @OneToMany(mappedBy = "link") var comments: MutableList<Comment> = ArrayList(),
    @Id @GeneratedValue var id: Long? = null,
    @CreatedBy var createdBy: String? = null,
    @CreatedDate var creationDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedBy var lastModifiedBy: String? = null,
    @LastModifiedDate var lastModifiedDate: LocalDateTime? = null
) {
    fun getDomainName(): String {
        val uri = URI(url)
        val domain: String = uri.getHost()
        return if (domain.startsWith("www.")) domain.substring(4) else domain
    }

    fun getPrettyTime(): String {
        return creationDate?.let {
            val pt: PrettyTime = BeanUtil.getBean(PrettyTime::class.java)
            pt.format(convertToDateViaInstant(it))
        } ?: "???"
    }

    private fun convertToDateViaInstant(dateToConvert: LocalDateTime): Date {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant())
    }
}