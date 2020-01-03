package com.simgle.starter.permissionmanagement.domain.service

import com.simgle.starter.infrastructure.accesscontrol.ApiGroup
import com.simgle.starter.infrastructure.accesscontrol.BizFunction
import org.springframework.beans.factory.getBeansOfType
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
open class ApiGroupService(
        private val applicationContext: ApplicationContext
) : ApplicationRunner {
    private val apiGroups = mutableListOf<ApiGroup>()
    override fun run(args: ApplicationArguments?) {
        applicationContext.getBeansOfType<ApiGroup>().forEach {
            apiGroups.add(it.value)
        }
    }

    open fun findAll(): MutableList<ApiGroup> {
        val copy = mutableListOf<ApiGroup>()
        apiGroups.forEach {
            copy.add(it)
        }
        return copy
    }
}