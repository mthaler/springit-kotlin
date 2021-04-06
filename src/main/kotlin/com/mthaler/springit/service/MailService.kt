package com.mthaler.springit.service

import com.mthaler.springit.domain.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.thymeleaf.spring5.SpringTemplateEngine
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.thymeleaf.context.Context
import java.lang.Exception
import javax.mail.internet.MimeMessage
import java.util.Locale

@Service
class MailService(val javaMailSender: JavaMailSender, val templateEngine: SpringTemplateEngine) {

    @Async
    fun sendEmail(to: String, subject: String, content: String, isMultiPart: Boolean, isHtml: Boolean) {
        log.debug("Sending Email")
        val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
        try {
            val message = MimeMessageHelper(mimeMessage, "UTF-8")
            message.setTo(to)
            message.setFrom("noreply@springit.com")
            message.setSubject(subject)
            message.setText(content, isHtml)
            javaMailSender.send(mimeMessage)
        } catch (e: Exception) {
            log.warn("Email could not be sent to user '{}': {}", to, e.message)
        }
    }

    @Async
    fun sendEmailFromTemplate(user: User, templateName: String, subject: String) {
        val locale = Locale.ENGLISH
        val context = Context(locale)
        context.setVariable("user", user)
        context.setVariable("baseURL", BASE_URL)
        val content = templateEngine.process(templateName, context)
        sendEmail(user.email, subject, content, false, true)
    }

    @Async
    fun sendActivationEmail(user: User) {
        log.debug("Sending activation email to '{}'", user.email)
        sendEmailFromTemplate(user, "email/activation", "Springit User Activation")
    }

    @Async
    fun sendWelcomeEmail(user: User) {
        log.debug("Sending activation email to '{}'", user.email)
        sendEmailFromTemplate(user, "email/welcome", "Welcome new Springit User")
    }

    companion object {

        private val log: Logger = LoggerFactory.getLogger(MailService::class.java)

        private const val BASE_URL = "http://localhost:8080"
    }
}