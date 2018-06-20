package ru.mmishak.bicyclewalks.data.database.gateways

import ru.mmishak.bicyclewalks.data.entities.Entity

interface Gateway<T : Entity> {
    fun read(id: Int): T?

    fun update(entity: T)

    fun getAll(): List<T>

    fun delete(id: Int)

    fun create(entity: T)
}