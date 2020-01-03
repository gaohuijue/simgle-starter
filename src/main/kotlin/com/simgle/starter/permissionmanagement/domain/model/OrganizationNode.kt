package com.simgle.starter.permissionmanagement.domain.model

interface OrganizationNode {
    fun getParent(): OrganizationNode?
    fun getChildren(): List<OrganizationNode>?
}