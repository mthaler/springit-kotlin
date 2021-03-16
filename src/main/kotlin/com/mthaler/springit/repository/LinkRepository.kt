package com.mthaler.springit.repository

import com.mthaler.springit.domain.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository: JpaRepository<Link, Long>