package ru.mmishak.bicyclewalks.data.database.gateways

import ru.mmishak.bicyclewalks.data.entities.HumanEntity

interface Gateway<T> {
    fun read(id: Int): T

    fun update(id: Int, entity: T)

    fun getAll(): List<T>

    fun delete(id: Int)

    fun create(entity: T)
}