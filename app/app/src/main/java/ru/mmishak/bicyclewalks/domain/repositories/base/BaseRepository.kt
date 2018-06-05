package ru.mmishak.bicyclewalks.domain.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.Entity
import kotlin.collections.ArrayList

interface BaseRepository<T : Entity> {
    fun generateId(): Int

    fun getAll(callback: (isSuccess: Boolean, entities: List<T>) -> Unit)

    fun get(id: Int, callback: (entity: T?) -> Unit)

    fun delete(entity: T, callback: ((isSuccess: Boolean) -> Unit)? = null)

    fun saveChanges(entity: T, callback: ((isSuccess: Boolean) -> Unit)? = null)
}