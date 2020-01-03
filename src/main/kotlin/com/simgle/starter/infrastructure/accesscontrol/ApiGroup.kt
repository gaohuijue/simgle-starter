package com.simgle.starter.infrastructure.accesscontrol

interface ApiGroup {
    // TODO 重复校验
    val id: String
    val name: String
    val permissions: List<Permission>
}