package com.simgle.starter.permissionmanagement.domain.repository

import com.simgle.starter.infrastructure.persistence.AbstractAuditingJpaRepository
import com.simgle.starter.permissionmanagement.domain.model.User
import org.springframework.stereotype.Repository

@Repository
open class UserRepository : AbstractAuditingJpaRepository<User, String>(User::class.java)
