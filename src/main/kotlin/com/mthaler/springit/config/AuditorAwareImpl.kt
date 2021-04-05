package com.mthaler.springit.config

import com.mthaler.springit.domain.User
import org.springframework.data.domain.AuditorAware
import java.util.*
import org.springframework.security.core.context.SecurityContextHolder

class AuditorAwareImpl: AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return if (SecurityContextHolder.getContext().authentication == null) {
            Optional.of("master@gmail.com")
        } else Optional.of((SecurityContextHolder.getContext().authentication.principal as User).email)
    }
}