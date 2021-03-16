package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Vote(@Id @GeneratedValue var id: Long, var vote: Int): Auditable()
