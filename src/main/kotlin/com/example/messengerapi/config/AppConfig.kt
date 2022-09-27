package com.example.messengerapi.config

import com.example.messengerapi.components.AccountValidateInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig: WebMvcConfigurer {
    @Autowired
    lateinit var accountValidateInterceptor: AccountValidateInterceptor
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(accountValidateInterceptor)
        super.addInterceptors(registry)
    }
}