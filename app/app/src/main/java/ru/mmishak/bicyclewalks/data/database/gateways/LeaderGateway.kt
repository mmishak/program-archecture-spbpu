package ru.mmishak.bicyclewalks.data.database.gateways

import ru.mmishak.bicyclewalks.data.entities.HumanEntity

object LeaderGateway : Gateway<HumanEntity> {
    override fun read(id: Int): HumanEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(id: Int, entity: HumanEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<HumanEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(entity: HumanEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}