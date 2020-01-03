package com.simgle.starter.permissionmanagement.domain.service

import com.simgle.starter.infrastructure.accesscontrol.BizFunction
import org.springframework.beans.factory.getBeansOfType
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
open class BizFunctionService(
        private val applicationContext: ApplicationContext
) : ApplicationRunner {
    private val bizFunctions = mutableListOf<BizFunction>()
    override fun run(args: ApplicationArguments?) {
        applicationContext.getBeansOfType<BizFunction>().forEach {
            bizFunctions.add(it.value)
        }
    }

    open fun findAll(): MutableList<BizFunction> {
        val copy = mutableListOf<BizFunction>()
        bizFunctions.forEach {
            copy.add(it)
        }
        return copy
    }

    open fun getBizFunctionByPermissionId(permissionId: String): BizFunction? {
        bizFunctions.forEach {
            if (it.permissions.find { it.getId() == permissionId } != null) {
                return it
            }
        }
        return null
    }
}