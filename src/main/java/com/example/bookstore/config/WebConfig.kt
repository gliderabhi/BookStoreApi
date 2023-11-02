package com.example.bookstore.config

import org.h2.server.web.WebServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

@Configuration
@EnableWebMvc
open class WebConfig : WebMvcConfigurer {

    @Bean
    open fun h2servletRegistration(): ServletRegistrationBean<*> {
        val registrationBean: ServletRegistrationBean<*> = ServletRegistrationBean(WebServlet())
        registrationBean.addUrlMappings("/console/*")
        return registrationBean
    }

    @Bean
    open fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    override fun getValidator(): LocalValidatorFactoryBean {
        val validatorFactoryBean = LocalValidatorFactoryBean()
        validatorFactoryBean.setValidationMessageSource(messageSource())
        return validatorFactoryBean
    }

    @Bean
    open fun localeResolver(): LocaleResolver {
        val localeResolver = AcceptHeaderLocaleResolver()
        localeResolver.defaultLocale = Locale.ENGLISH
        return localeResolver
    }

}