package com.simgle.starter.infrastructure.cors

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("cors")
open class CorsConfigProperties {
    open var domains: MutableMap<String, CorsMapping>? = null
}

class CorsMapping {
    var mapping: String? = null
    var origins: MutableList<String> = mutableListOf()
}