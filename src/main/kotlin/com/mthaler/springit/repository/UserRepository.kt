package com.mthaler.springit.repository

import com.mthaler.springit.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String?): Optional<User>
}