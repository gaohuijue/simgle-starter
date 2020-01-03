package com.simgle.starter.permissionmanagement.domain.service

import com.simgle.starter.permissionmanagement.domain.model.RolePermission
import com.simgle.starter.permissionmanagement.domain.repository.RolePermissionRepository
import org.springframework.stereotype.Service

@Service
open class RolePermissionService(
        private val rolePermissionRepository: RolePermissionRepository
) {
    open fun findAllByRoleId(roleId: String): List<RolePermission> {
        return rolePermissionRepository.findAll("roleId", roleId)
    }
}