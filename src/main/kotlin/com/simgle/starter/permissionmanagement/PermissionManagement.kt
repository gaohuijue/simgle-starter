package com.simgle.starter.permissionmanagement

import com.fasterxml.jackson.annotation.JsonIgnore
import com.simgle.starter.infrastructure.accesscontrol.BizFunction
import com.simgle.starter.infrastructure.accesscontrol.Permission
import org.springframework.stereotype.Component

@Component
open class PermissionManagement : BizFunction {
    override val id = ID
    override val name = "权限管理"
    override val permissions = permissionList

    companion object {
        private const val ID = "PermissionManagement"
        const val PERMISSION_READ = "$ID:read"
        const val PERMISSION_WRITE = "$ID:write"
        val permissionList = listOf(
                Permission(PERMISSION_READ, "权限管理:读"),
                Permission(PERMISSION_WRITE, "权限管理:写")
        )
    }
}