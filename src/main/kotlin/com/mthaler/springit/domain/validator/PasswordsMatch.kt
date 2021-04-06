package com.mthaler.springit.domain.validator

import java.lang.annotation.Documented
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [PasswordsMatchValidator::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordsMatch(val message: String = "Password & Password Confirmation do not match.",
                                val groups: Array<KClass<*>> = emptyArray(),
                                val payload: Array<KClass<out Payload>> = emptyArray())