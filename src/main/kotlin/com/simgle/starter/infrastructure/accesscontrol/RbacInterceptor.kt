package com.simgle.starter.infrastructure.accesscontrol

import com.simgle.core.mvc.AbstractRbacInterceptor
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
open class RbacInterceptor : AbstractRbacInterceptor() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return super.preHandle(request, response, handler)
    }
}