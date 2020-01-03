package com.simgle.starter.handleexception

import com.simgle.core.view.DataView
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.HandlerMethod
import java.util.logging.Level
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
open class ExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    open fun handle(e: Exception, handlerMethod: HandlerMethod, servletRequest: HttpServletRequest): DataView {
        Logger.getLogger(javaClass.name).log(Level.SEVERE,
                "Request to ${servletRequest.requestURI},exception from ${handlerMethod.method}:${e.message}", e)
        return DataView.build {
            success = false
            message = e.message
        }
    }
}