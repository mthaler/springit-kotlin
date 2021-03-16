package com.mthaler.springit.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Vote(@Id @GeneratedValue val id: Long, val vote: Int)
