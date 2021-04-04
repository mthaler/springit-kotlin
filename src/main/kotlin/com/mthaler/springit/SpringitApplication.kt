package com.mthaler.springit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.ocpsoft.prettytime.PrettyTime
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableJpaAuditing
class SpringitApplication {

	@Bean
	fun prettyTime(): PrettyTime? {
		return PrettyTime()
	}
}

fun main(args: Array<String>) {
	runApplication<SpringitApplication>(*args)
}
