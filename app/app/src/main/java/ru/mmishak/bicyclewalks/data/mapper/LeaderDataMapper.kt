package ru.mmishak.bicyclewalks.data.mapper

import ru.mmishak.bicyclewalks.data.entities.HumanEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader

object LeaderDataMapper : Mapper<HumanEntity, Leader> {
    override fun transform(entity: HumanEntity): Leader {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transform(collection: List<HumanEntity>): List<Leader> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}