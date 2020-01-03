package com.simgle.starter.infrastructure.accesscontrol

import com.simgle.core.rbac.RbacPermission

class Permission(private var id: String, var name: String) : RbacPermission {

    override fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }
}