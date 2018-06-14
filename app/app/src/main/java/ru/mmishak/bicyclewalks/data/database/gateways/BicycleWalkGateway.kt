package ru.mmishak.bicyclewalks.data.database.gateways

import ru.mmishak.bicyclewalks.data.entities.BicycleWalkEntity

object BicycleWalkGateway : Gateway<BicycleWalkEntity> {
    override fun read(id: Int): BicycleWalkEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(id: Int, entity: BicycleWalkEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getAll(): List<BicycleWalkEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(entity: BicycleWalkEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}