package com.mthaler.springit.service

import com.mthaler.springit.domain.User
import com.mthaler.springit.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(val userRepository: UserRepository) {

    fun register(user: User): User {
        return user
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }

    @Transactional
    fun saveUsers(vararg users: User) {
        for (user in users) {
            logger.info("Saving User: " + user.email)
            userRepository.save(user)
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)
    }
}