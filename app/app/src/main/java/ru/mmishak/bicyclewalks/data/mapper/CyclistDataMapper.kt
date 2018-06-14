package ru.mmishak.bicyclewalks.data.mapper

import ru.mmishak.bicyclewalks.data.entities.HumanEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Cyclist

object CyclistDataMapper : Mapper<HumanEntity, Cyclist> {
    override fun transform(entity: HumanEntity): Cyclist {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transform(collection: List<HumanEntity>): List<Cyclist> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}