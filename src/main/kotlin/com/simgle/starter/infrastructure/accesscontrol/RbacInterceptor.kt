package com.simgle.starter.infrastructure.accesscontrol

import com.simgle.core.Constant
import com.simgle.core.mvc.AbstractRbacInterceptor
import com.simgle.core.rbac.RbacSession
import com.simgle.core.rbac.RequiredPermissions
import com.simgle.core.tool.ThreadBinds
import com.simgle.starter.MvcConstant
import com.simgle.starter.permissionmanagement.domain.model.User
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.full.primaryConstructor

@Component
open class RbacInterceptor : AbstractRbacInterceptor() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            val handlerMethod = handler as HandlerMethod
            val requiredPermissionsOfMethod = handlerMethod.getMethodAnnotation(RequiredPermissions::class.java)
            val requiredPermissionsOfClass = handlerMethod.beanType.getAnnotation(RequiredPermissions::class.java)

            if (requiredPermissionsOfMethod == null && requiredPermissionsOfClass == null) {
                return super.preHandle(request, response, handler)
            }

            val session = ThreadBinds.get(Constant.RBAC_SESSION) as RbacSession?
            if (session == null) {
                response.sendRedirect(MvcConstant.LOGIN_PAGE_URL)
                return false
            }

            if (requiredPermissionsOfMethod != null) {
                if (!canAccess(session.rbacUser as User, requiredPermissionsOfMethod)) {
                    return false
                }
                super.preHandle(request, response, handler)
            }

            if (requiredPermissionsOfClass != null) {
                if (!canAccess(session.rbacUser as User, requiredPermissionsOfClass)) {
                    return false
                }
                super.preHandle(request, response, handler)
            }
        }

        return super.preHandle(request, response, handler)
    }

    private fun canAccess(user: User, requiredPermissions: RequiredPermissions): Boolean {
        if (requiredPermissions.value.isNotBlank()) {
            return user.canAccess(requiredPermissions.value)
        }

        val evaluator = requiredPermissions.logicPolicy.primaryConstructor?.call()
                ?: throw RuntimeException()
        return evaluator.evaluate(user, user.permissions)
    }
}