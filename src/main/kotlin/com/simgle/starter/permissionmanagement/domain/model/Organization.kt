package com.simgle.starter.permissionmanagement.domain.model

import com.simgle.core.rbac.RbacOrganization
import com.simgle.starter.infrastructure.persistence.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Transient

/**
 * 简单实现组织机构和权限，要组织机构是为了用树组件。
 */
@Entity
@Table(name = "SYS_PM_ORGANIZATION")
open class Organization : BaseEntity(), RbacOrganization, OrganizationNode {
    @Column(name = "ID", columnDefinition = "varchar(32) COMMENT 'id'")
    private var id: String? = null
    @Column(name = "PARENT_ID", columnDefinition = "varchar(32) COMMENT '父id'")
    private var parentId: String? = null
    @Column(name = "NAME", columnDefinition = "varchar(32) COMMENT '显示名称'")
    open var name: String? = null
    @Column(name = "ENABLE", columnDefinition = "int COMMENT '启用'")
    open var enable: Int? = null
    @Column(name = "MEMO", columnDefinition = "varchar(200) COMMENT '备注'")
    open var memo: String? = null
    @Transient
    private var parent: OrganizationNode? = null
    @Transient
    private var children: List<OrganizationNode>? = null

    override fun getParent(): OrganizationNode? {
        return this.parent
    }

    open fun setParent(parent: OrganizationNode) {
        this.parent = parent
    }

    override fun getId(): String {
        return this.id ?: throw RuntimeException("数据异常。")
    }

    open fun setId(id: String) {
        this.id = id
    }


    override fun getChildren(): List<OrganizationNode>? {
        return children
    }

    open fun setChildren(children: List<OrganizationNode>) {
        this.children = children
    }

    override fun verification() {
        val errMsg = StringBuilder()
        this.id ?: errMsg.append("ID不能为空;")
        this.name ?: errMsg.append("NAME不能为空;")
        this.parentId ?: errMsg.append("父节点ID不能为空;")
        if (errMsg.isNotEmpty()) throw RuntimeException(errMsg.toString())
    }
}