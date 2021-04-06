package com.mthaler.springit.service

import com.mthaler.springit.domain.Role
import com.mthaler.springit.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(val roleRepository: RoleRepository) {

    fun findByName(name: String): Role = roleRepository.findByName(name)
}