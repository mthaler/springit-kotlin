package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Vote(
    var vote: Int,
    @Id @GeneratedValue var id: Long? = null
): Auditable()
