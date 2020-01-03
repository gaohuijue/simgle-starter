package com.simgle.starter.permissionmanagement.domain.model

import com.simgle.starter.infrastructure.persistence.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "SYS_PM_PERMISSION")
open class RolePermission : BaseEntity() {
    @Column(name = "ID", columnDefinition = "varchar(32) COMMENT '权限标识'")
    open var id: String? = null
    @Column(name = "ROLE_ID", columnDefinition = "varchar(32) COMMENT '角色ID'")
    open var roleId: String? = null
}