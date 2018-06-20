package ru.mmishak.bicyclewalks.data.database.gateways

import ru.mmishak.bicyclewalks.data.entities.ReviewEntity

object ReviewGateway : Gateway<ReviewEntity> {
    override fun read(id: Int): ReviewEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(entity: ReviewEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<ReviewEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(entity: ReviewEntity): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}