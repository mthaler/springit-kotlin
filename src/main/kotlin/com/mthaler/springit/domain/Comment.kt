package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Comment(@Id @GeneratedValue var id: Long, var body: String, @ManyToOne var link: Link?): Auditable()