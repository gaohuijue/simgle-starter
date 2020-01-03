package com.simgle.starter.permissionmanagement

import com.simgle.core.Constant
import com.simgle.core.rbac.RbacPermission
import com.simgle.starter.infrastructure.accesscontrol.BizFunction
import com.simgle.starter.permissionmanagement.domain.model.User
import com.simgle.starter.permissionmanagement.domain.service.BizFunctionService
import org.springframework.stereotype.Component

@Component
open class RootUser(
        private val bizFunctionService: BizFunctionService
) : User() {
    override var name: String? = "ROOT"
    override var password: String? = "1"
    override var bizFunctions: MutableList<BizFunction> = mutableListOf()
        get() = bizFunctionService.findAll()

    override fun getId(): String {
        return Constant.RBAC_ROOT_USER_ID
    }

    override fun canAccess(permission: RbacPermission): Boolean {
        return true
    }
}