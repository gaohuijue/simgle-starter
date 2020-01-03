package com.simgle.starter.infrastructure.accesscontrol

import com.simgle.core.rbac.RbacUser

interface AuthableUser : RbacUser {
    fun authenticate(authInfo: Any?): Boolean
}