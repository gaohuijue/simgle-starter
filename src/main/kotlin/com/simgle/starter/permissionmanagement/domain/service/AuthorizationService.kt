package com.simgle.starter.permissionmanagement.domain.service

import org.springframework.stereotype.Service

@Service
open class AuthorizationService(
        private val roleService: RoleService
) {
    open fun findAllPermissionsOfUser(userId: String) {

    }
}