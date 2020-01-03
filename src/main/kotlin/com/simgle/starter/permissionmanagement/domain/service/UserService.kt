package com.simgle.starter.permissionmanagement.domain.service

import com.simgle.core.Constant
import com.simgle.starter.permissionmanagement.RootUser
import com.simgle.starter.permissionmanagement.domain.model.User
import com.simgle.starter.permissionmanagement.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
open class UserService(
        private val userRepository: UserRepository,
        private val roleService: RoleService,
        private val rolePermissionService: RolePermissionService,
        private val bizFunctionService: BizFunctionService,
        private val rootUser: RootUser
) {
    /**
     * 会重新获取用户的权限信息
     */
    open fun findByIdForAuth(id: String): User? {
        if (id == Constant.RBAC_ROOT_USER_ID) {
            return rootUser
        }

        val users = userRepository.findAll("id", id)
        val user = when (users.size) {
            0 -> return null
            1 -> users[0]
            else -> throw RuntimeException("数据异常,id=${id}数据有多条。")
        }

        // 给用户添加角色
        roleService.findByUserId(user.getId())
                .forEach {
                    user.roles.add(it)
                }

        // 给用户添加功能和权限
        user.roles.forEach { role ->
            val rolePermissions = rolePermissionService.findAllByRoleId(role.getId())
            for (rolePermission in rolePermissions) {
                val bizFunction = bizFunctionService.getBizFunctionByPermissionId(rolePermission.id
                        ?: throw RuntimeException("数据异常")) ?: continue
                val userBizFunction = user.bizFunctions.find { it.id == bizFunction.id }
                if (userBizFunction != null) {
                    user.bizFunctions.add(userBizFunction)
                }

                val permission = bizFunction.permissions.find { it.getId() == rolePermission.id }
                        ?: throw RuntimeException("程序异常")
                user.permissions.add(permission)
            }
        }

        //TODO 接口权限暂不考虑
        return user
    }
}