package com.simgle.starter.infrastructure.accesscontrol

import com.simgle.core.rbac.RbacSession
import com.simgle.core.rbac.RbacUser

class Session(override var rbacUser: RbacUser) : RbacSession