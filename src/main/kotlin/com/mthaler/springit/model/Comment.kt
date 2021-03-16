package com.mthaler.springit.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Comment(@Id @GeneratedValue val id: Long, val body: String)