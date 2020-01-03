package com.simgle.starter.permissionmanagement.domain.service

import com.simgle.starter.permissionmanagement.domain.model.Role
import com.simgle.starter.permissionmanagement.domain.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
open class RoleService(
        private val roleRepository: RoleRepository
) {
    open fun findByUserId(userId: String): List<Role> {
        return roleRepository.findAll("userId", userId)
    }
}