package com.simgle.starter.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.simgle.core.Constant
import com.simgle.core.view.DataView
import com.simgle.starter.FirstApiGroup
import com.simgle.starter.infrastructure.accesscontrol.Session
import com.simgle.starter.permissionmanagement.domain.service.AuthenticateService
import com.simgle.starter.permissionmanagement.domain.service.AuthorizationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
open class AuthController(
        private val authenticateService: AuthenticateService,
        private val authorizationService: AuthorizationService
) {
    @PostMapping(FirstApiGroup.LOGIN)
    open fun login(request: HttpServletRequest, response: HttpServletResponse): DataView {
        val json = request.reader.readLine() ?: throw RuntimeException("参数错误。")
        val om = ObjectMapper()
        val authInfo = om.readValue<Map<String, String>>(json)
        val user = authenticateService.authenticate(
                authInfo["username"] ?: throw RuntimeException("参数错误。"),
                authInfo["password"] ?: throw RuntimeException("参数错误。")
        )

        request.session.setAttribute(Constant.RBAC_SESSION, Session(user))

        return DataView.build {
            success = true
            data = mapOf(
                    "username" to user.getId(),
                    "name" to user.name,
                    "bizFunctionsIds" to user.bizFunctions.map {
                        it.id
                    }
            )
        }
    }

    @PostMapping(FirstApiGroup.LOGOUT)
    open fun logout(request: HttpServletRequest, response: HttpServletResponse): DataView {
        request.session.removeAttribute(Constant.RBAC_SESSION)
        return DataView.build {
            success = true
        }
    }
}
