package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Link(@Id @GeneratedValue var id: Long, var title: String, var url: String, @OneToMany(mappedBy = "link") var comments: List<Comment> = emptyList()): Auditable()