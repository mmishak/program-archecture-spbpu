package ru.mmishak.bicyclewalks.data.mapper

import ru.mmishak.bicyclewalks.data.entities.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation.BicycleWalk

object BicycleWalkDataMapper : Mapper<BicycleWalkEntity, BicycleWalk> {
    override fun transform(entity: BicycleWalkEntity): BicycleWalk {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transform(collection: List<BicycleWalkEntity>): List<BicycleWalk> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}