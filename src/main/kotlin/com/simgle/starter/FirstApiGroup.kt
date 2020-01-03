package com.simgle.starter

import com.simgle.starter.infrastructure.accesscontrol.ApiGroup
import com.simgle.starter.infrastructure.accesscontrol.Permission
import org.springframework.stereotype.Component

@Component
open class FirstApiGroup : ApiGroup {
    override val id = "FirstApiGroup"
    override val name = "一方接口组"
    override val permissions = listOf(
            Permission(LOGIN, "用户登录"),
            Permission(LOGIN, "用户登出")
    )

    companion object {
        const val LOGIN = "/login"
        const val LOGOUT = "/logout"
    }
}