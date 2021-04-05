package com.mthaler.springit.repository

import com.mthaler.springit.domain.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long>