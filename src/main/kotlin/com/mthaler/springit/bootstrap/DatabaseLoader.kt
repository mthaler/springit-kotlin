package com.mthaler.springit.bootstrap

import com.mthaler.springit.domain.Link
import com.mthaler.springit.domain.Role
import com.mthaler.springit.domain.User
import com.mthaler.springit.repository.CommentRepository
import com.mthaler.springit.repository.LinkRepository
import com.mthaler.springit.repository.RoleRepository
import com.mthaler.springit.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@Component
class DatabaseLoader(val linkRepository: LinkRepository,
                     val commentRepository: CommentRepository,
                     val userRepository: UserRepository,
                     val roleRepository: RoleRepository): CommandLineRunner {

    override fun run(vararg args: String?) {

        // add users and roles
        addUsersAndRoles();

        val links: MutableMap<String, String> = HashMap()
        links["Securing Spring Boot APIs and SPAs with OAuth 2.0"] =
            "https://auth0.com/blog/securing-spring-boot-apis-and-spas-with-oauth2/?utm_source=reddit&utm_medium=sc&utm_campaign=springboot_spa_securing"
        links["Easy way to detect Device in Java Web Application using Spring Mobile - Source code to download from GitHub"] =
            "https://www.opencodez.com/java/device-detection-using-spring-mobile.htm"
        links["Tutorial series about building microservices with SpringBoot (with Netflix OSS)"] =
            "https://medium.com/@marcus.eisele/implementing-a-microservice-architecture-with-spring-boot-intro-cdb6ad16806c"
        links["Detailed steps to send encrypted email using Java / Spring Boot - Source code to download from GitHub"] =
            "https://www.opencodez.com/java/send-encrypted-email-using-java.htm"
        links["Build a Secure Progressive Web App With Spring Boot and React"] =
            "https://dzone.com/articles/build-a-secure-progressive-web-app-with-spring-boo"
        links["Building Your First Spring Boot Web Application - DZone Java"] =
            "https://dzone.com/articles/building-your-first-spring-boot-web-application-ex"
        links["Building Microservices with Spring Boot Fat (Uber) Jar"] =
            "https://jelastic.com/blog/building-microservices-with-spring-boot-fat-uber-jar/"
        links["Spring Cloud GCP 1.0 Released"] =
            "https://cloud.google.com/blog/products/gcp/calling-java-developers-spring-cloud-gcp-1-0-is-now-generally-available"
        links["Simplest way to Upload and Download Files in Java with Spring Boot - Code to download from Github"] =
            "https://www.opencodez.com/uncategorized/file-upload-and-download-in-java-spring-boot.htm"
        links["Add Social Login to Your Spring Boot 2.0 app"] =
            "https://developer.okta.com/blog/2018/07/24/social-spring-boot"
        links["File download example using Spring REST Controller"] =
            "https://www.jeejava.com/file-download-example-using-spring-rest-controller/"

        links.forEach { (k: Any, v: Any) ->
            linkRepository.save<Link>(Link(k, v))
        }

        val linkCount = linkRepository.count()
        println("Number of links in the database: $linkCount")
    }

    private fun addUsersAndRoles() {
        val encoder = BCryptPasswordEncoder()
        val secret = "{bcrypt}" + encoder.encode("password")
        val userRole = Role("ROLE_USER")
        roleRepository.save<Role>(userRole)
        val adminRole = Role("ROLE_ADMIN")
        roleRepository.save<Role>(adminRole)
        val user = User("user@gmail.com", secret, true)
        user.addRole(userRole)
        userRepository.save<User>(user)
        val admin = User("admin@gmail.com", secret, true)
        admin.addRole(adminRole)
        userRepository.save<User>(admin)
        val master = User("master@gmail.com", secret, true)
        master.addRoles(setOf(userRole, adminRole))
        userRepository.save<User>(master)
    }
}