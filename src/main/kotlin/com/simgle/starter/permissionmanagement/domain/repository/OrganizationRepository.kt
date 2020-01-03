package com.simgle.starter.permissionmanagement.domain.repository

import com.simgle.starter.infrastructure.persistence.AbstractAuditingJpaRepository
import com.simgle.starter.permissionmanagement.domain.model.Organization
import org.springframework.stereotype.Repository

@Repository
open class OrganizationRepository : AbstractAuditingJpaRepository<Organization, String>(Organization::class.java)