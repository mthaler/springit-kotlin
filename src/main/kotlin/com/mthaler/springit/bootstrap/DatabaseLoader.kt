package com.mthaler.springit.bootstrap

import com.mthaler.springit.domain.Comment
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
import java.util.HashMap

@Component
class DatabaseLoader(val linkRepository: LinkRepository,
                     val commentRepository: CommentRepository,
                     val userRepository: UserRepository,
                     val roleRepository: RoleRepository): CommandLineRunner {

    private val users: MutableMap<String, User> = HashMap()

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
            val u1 = users["user@gmail.com"]
            val u2 = users["super@gmail.com"]
            val link = Link(k, v)
            if (k.startsWith("Build")) {
                link.user = u1
            } else {
                link.user = u2
            }
            linkRepository.save(link)

            // we will do something with comments later
            // we will do something with comments later
            val spring = Comment("Thank you for this link related to Spring Boot. I love it, great post!", link)
            val security = Comment("I love that you're talking about Spring Security", link)
            val pwa = Comment("What is this Progressive Web App thing all about? PWAs sound really cool.", link)
            val comments: Array<Comment> = arrayOf<Comment>(spring, security, pwa)
            for (comment in comments) {
                commentRepository.save(comment)
                link.addComment(comment)
            }
        }

        val linkCount = linkRepository.count()
        println("Number of links in the database: $linkCount")
    }

    private fun addUsersAndRoles() {
        val encoder = BCryptPasswordEncoder()
        val secret = "{bcrypt}" + encoder.encode("password")

        val userRole = Role("ROLE_USER")
        roleRepository.save(userRole)
        val adminRole = Role("ROLE_ADMIN")
        roleRepository.save(adminRole)

        val user = User("user@gmail.com", secret, true, "Joe", "User", "joedirt")
        user.addRole(userRole)
        user.confirmPassword = secret
        userRepository.save(user)
        users.put("user@gmail.com", user)

        val admin = User("admin@gmail.com", secret, true, "Joe", "Admin", "masteradmin")
        admin.alias = "joeadmin"
        admin.addRole(adminRole)
        admin.confirmPassword = secret
        userRepository.save(admin)
        users.put("admin@gmail.com", admin)

        val master = User("super@gmail.com", secret, true, "Super", "User", "superduper")
        master.addRoles(setOf(userRole, adminRole))
        master.confirmPassword = secret
        userRepository.save(master)
        users.put("super@gmail.com", master)
    }
}