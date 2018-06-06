package ru.mmishak.bicyclewalks.data.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.Entity
import kotlin.collections.ArrayList

interface BaseRepository<T : Entity> {
    fun generateId(): Int

    fun getAll(): List<T>

    fun get(id: Int): T?

    fun delete(entity: T)

    fun saveChanges(entity: T)
}