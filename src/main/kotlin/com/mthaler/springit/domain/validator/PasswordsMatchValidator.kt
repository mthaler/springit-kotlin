package com.mthaler.springit.domain.validator

import com.mthaler.springit.domain.User
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class PasswordsMatchValidator: ConstraintValidator<PasswordsMatch, User> {

    override fun isValid(user: User, context: ConstraintValidatorContext): Boolean = user.password == user.confirmPassword
}