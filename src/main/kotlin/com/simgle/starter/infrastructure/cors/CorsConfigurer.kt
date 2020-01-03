package com.simgle.starter.infrastructure.cors

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class CorsConfigurer(
        private val corsConfigProperties: CorsConfigProperties
) : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        corsConfigProperties.domains?.forEach {
            registry.addMapping(it.value.mapping ?: throw RuntimeException("配置错误"))
                    .allowedOrigins(*it.value.origins.toTypedArray())
        }
    }
}