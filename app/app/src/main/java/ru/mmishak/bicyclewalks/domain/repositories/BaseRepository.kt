package ru.mmishak.bicyclewalks.domain.repositories

import ru.mmishak.bicyclewalks.domain.entities.Entity

interface BaseRepository<T : Entity> {
    fun getAll(): List<T>

    fun get(id: Int): T

    fun delete(entity: T)

    fun add(entity: T)

    fun saveChanges(entity: T)
}