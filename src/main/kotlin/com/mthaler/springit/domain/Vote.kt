package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Vote(
    var direction: Short,
    @ManyToOne
    var link: Link,
    @Id @GeneratedValue var id: Long? = null
): Auditable()
