package com.mthaler.springit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.ocpsoft.prettytime.PrettyTime
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringitApplication {

	@Bean
	fun prettyTime(): PrettyTime? {
		return PrettyTime()
	}
}

fun main(args: Array<String>) {
	runApplication<SpringitApplication>(*args)
}
