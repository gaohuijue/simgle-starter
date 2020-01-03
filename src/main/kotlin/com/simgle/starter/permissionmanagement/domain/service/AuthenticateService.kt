package com.simgle.starter.permissionmanagement.domain.service

import com.simgle.starter.permissionmanagement.domain.model.User
import org.springframework.stereotype.Service

@Service
open class AuthenticateService(
        private val userService: UserService
) {
    open fun authenticate(username: String, password: String): User {
        val user = userService.findByIdForAuth(username) ?: throw RuntimeException("用户名或密码错误。")

        if (!user.authenticate(password)) {
            throw RuntimeException("用户名或密码错误")
        }

        return user
    }
}