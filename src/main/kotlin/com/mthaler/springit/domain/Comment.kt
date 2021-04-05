package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Comment(
    var body: String,
    @ManyToOne var link: Link?,
    @Id @GeneratedValue var id: Long? = null
): Auditable()