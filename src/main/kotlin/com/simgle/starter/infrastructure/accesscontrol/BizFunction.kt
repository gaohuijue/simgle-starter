package com.simgle.starter.infrastructure.accesscontrol

interface BizFunction {
    // TODO 重复校验
    val id: String
    val name: String
    val permissions: List<Permission>
}