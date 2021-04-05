package com.mthaler.springit.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Role(
    var name: String,
    @ManyToMany( mappedBy = "roles")
    var users: MutableCollection<User> = ArrayList(),
    @Id @GeneratedValue
    var id: Long? = null
)