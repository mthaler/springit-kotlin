package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Link(@Id @GeneratedValue val id: Long, val title: String, val url: String)