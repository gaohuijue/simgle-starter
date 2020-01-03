package com.simgle.starter.permissionmanagement.domain.model

import com.simgle.core.rbac.RbacPermission
import com.simgle.core.rbac.RbacRole
import com.simgle.starter.infrastructure.persistence.BaseEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "SYS_PM_ROLE")
open class Role : BaseEntity(), RbacRole {
    override fun getPermissions(): List<RbacPermission> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getId(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "varchar(100) COMMENT '角色ID'")
    private var id: String? = null
    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(100) COMMENT '角色名称'")
    open var name: String? = null
    @Column(name = "CREATOR", columnDefinition = "varchar(100) COMMENT '创建人'")
    open var creator: String? = null
    @Column(name = "CREATE_TIME", columnDefinition = "timestamp COMMENT '创建时间'")
    open var createTime: Date? = null
    @Column(name = "MEMO", columnDefinition = "varchar(200) COMMENT '备注'")
    open var memo: String? = null

    override fun verification() {
        this.name ?: throw RuntimeException("NAME不能为空")
        this.id ?: throw RuntimeException("ID不能为空")
    }
}