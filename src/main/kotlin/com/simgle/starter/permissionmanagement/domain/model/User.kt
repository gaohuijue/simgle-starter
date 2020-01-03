package com.simgle.starter.permissionmanagement.domain.model

import com.simgle.core.rbac.RbacPermission
import com.simgle.core.rbac.RbacRole
import com.simgle.starter.infrastructure.accesscontrol.AuthableUser
import com.simgle.starter.infrastructure.accesscontrol.BizFunction
import com.simgle.starter.infrastructure.persistence.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "SYS_PM_USER")
open class User : BaseEntity(), AuthableUser {
    @Column(name = "ID", columnDefinition = "varchar(32) COMMENT 'id、用户名'")
    private var id: String? = null
    @Column(name = "NAME", columnDefinition = "varchar(32) COMMENT '显示名称'")
    open var name: String? = null
    @Column(name = "PASSWORD", columnDefinition = "varchar(64) COMMENT '密码'")
    open var password: String? = null
    @Column(name = "SORT", columnDefinition = "int(8) COMMENT '排序码'")
    open var sort: Int? = 0
    @Column(name = "ENABLE", columnDefinition = "tinyint(1) COMMENT '启用'")
    open var enable: Int? = 1
    @Column(name = "MEMO", columnDefinition = "varchar(200) COMMENT '备注'")
    open var memo: String? = null
    @Transient
    open val roles: MutableList<RbacRole> = mutableListOf()
    @Transient
    open val permissions: MutableList<RbacPermission> = mutableListOf()
    @Transient
    open val bizFunctions: MutableList<BizFunction> = mutableListOf()

    override fun getId(): String {
        return this.id ?: throw RuntimeException("数据异常。")
    }

    open fun setId(id: String) {
        this.id = id
    }

    override fun canAccess(permission: RbacPermission): Boolean {
        permissions.forEach {
            if (it.getId() == permission.getId()) {
                return true
            }
        }
        return false
    }

    fun canAccess(permissionId: String): Boolean {
        permissions.forEach {
            if (it.getId() == permissionId) {
                return true
            }
        }
        return false
    }

    override fun authenticate(authInfo: Any?): Boolean {
        val password = (authInfo as String?) ?: return false
        return this.password == password
    }

    override fun verification() {
        this.id ?: throw RuntimeException("ID不能为空")
        this.name ?: throw RuntimeException("姓名不能为空")
        this.password ?: throw RuntimeException("密码不能为空")
    }
}