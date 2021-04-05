package com.mthaler.springit.domain

import com.mthaler.springit.service.BeanUtil
import org.hibernate.validator.constraints.URL
import java.net.URI
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import org.ocpsoft.prettytime.PrettyTime
import java.time.ZoneId
import java.util.*
import javax.validation.constraints.NotEmpty
import kotlin.collections.ArrayList

@Entity
class Link(
    @NotEmpty(message = "Please enter a title.")
    var title: String = "",
    @NotEmpty(message = "Please enter a URL.")
    @URL(message = "Please enter a valid URL.")
    var url: String = "",
    @OneToMany(mappedBy = "link") var comments: MutableList<Comment> = ArrayList(),
    @OneToMany(mappedBy = "link") var votes: MutableList<Vote> = ArrayList(),
    @Id @GeneratedValue var id: Long? = null
): Auditable() {

    private var voteCount = 0

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