package com.mthaler.springit.service

import com.mthaler.springit.domain.User
import com.mthaler.springit.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(val userRepository: UserRepository, val roleService: RoleService) {

    private val encoder = BCryptPasswordEncoder()

    fun register(user: User): User {
        // take the password from the form and encode
        val secret = "{bcrypt}" + encoder.encode(user.getPassword())
        user.password = secret

        // confirm password

        // assign a role to this user
        user.addRole(roleService.findByName("ROLE_USER"))

        // set an activation code

        // disable the user
        user.enabled = false

        // save user
        save(user)

        // send the activation email
        sendActivationEmail(user)

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

    fun sendActivationEmail(user: User) {
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)
    }
}