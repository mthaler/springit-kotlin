package com.mthaler.springit.domain

import com.mthaler.springit.service.BeanUtil
import org.ocpsoft.prettytime.PrettyTime
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Comment(
    var body: String = "",
    @ManyToOne var link: Link? = null
): Auditable() {

    @Id @GeneratedValue var id: Long? = null

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