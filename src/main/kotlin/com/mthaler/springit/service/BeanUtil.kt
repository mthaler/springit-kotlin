package com.mthaler.springit.service

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service

@Service
class BeanUtil: ApplicationContextAware {

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    companion object {

        private lateinit var context: ApplicationContext

        fun <T>getBean(beanClass: Class<T>): T = context.getBean(beanClass)
    }
}