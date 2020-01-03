package com.simgle.starter.permissionmanagement.domain.repository

import com.simgle.starter.infrastructure.persistence.AbstractAuditingJpaRepository
import com.simgle.starter.permissionmanagement.domain.model.RolePermission
import org.springframework.stereotype.Repository

@Repository
open class RolePermissionRepository : AbstractAuditingJpaRepository<RolePermission, String>(RolePermission::class.java)