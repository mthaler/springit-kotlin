package com.mthaler.springit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringitApplication

fun main(args: Array<String>) {
	runApplication<SpringitApplication>(*args)
}
