package com.simgle.starter.permissionmanagement.domain.repository

import com.simgle.starter.infrastructure.persistence.AbstractAuditingJpaRepository
import com.simgle.starter.permissionmanagement.domain.model.Role
import org.springframework.stereotype.Repository

@Repository
open class RoleRepository : AbstractAuditingJpaRepository<Role, String>(Role::class.java)