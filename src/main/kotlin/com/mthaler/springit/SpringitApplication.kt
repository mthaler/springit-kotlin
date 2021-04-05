package com.mthaler.springit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.ocpsoft.prettytime.PrettyTime
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect

@SpringBootApplication
@EnableTransactionManagement
class SpringitApplication {

	@Bean
	fun prettyTime(): PrettyTime? {
		return PrettyTime()
	}

	// TODO * Configuring this bean should not be needed once Spring Boot's Thymeleaf starter includes configuration
	// TODO   for thymeleaf-extras-springsecurity5 (instead of thymeleaf-extras-springsecurity4)
	@Bean
	fun securityDialect(): SpringSecurityDialect? {
		return SpringSecurityDialect()
	}
}

fun main(args: Array<String>) {
	runApplication<SpringitApplication>(*args)
}
