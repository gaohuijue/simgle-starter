package com.simgle.starter.persistence

import com.simgle.core.Constant
import com.simgle.core.rbac.RbacSession
import com.simgle.core.tool.ThreadBinds
import com.simgle.persistence.repository.AbstractJpaRepository
import java.util.*

abstract class AbstractAuditingJpaRepository<T, ID>(
        private val modelClazz: Class<T>
) : AbstractJpaRepository<T, ID>(modelClazz) {
    override fun save(entity: T) {
        val et = entity as BaseEntity
        et.operateTime = Date()
        et.operator = (ThreadBinds.get(Constant.RBAC_SESSION) as RbacSession?)?.getRbacUser()?.getId()
        super.save(entity)
    }

    override fun saveAll(entities: Iterable<T>) {
        val userId = (ThreadBinds.get(Constant.RBAC_SESSION) as RbacSession?)?.getRbacUser()?.getId()
        val now = Date()
        entities.forEach {
            val et = it as BaseEntity
            et.operateTime = now
            et.operator = userId
        }
        super.saveAll(entities)
    }
}