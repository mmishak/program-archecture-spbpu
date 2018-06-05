package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.users.User

fun <T : Entity> MutableList<T>.generateId(): Int {
    var id = 0
    while (this.any { it.id == id })
        id++
    return id
}

fun <T : Entity> MutableList<T>.find(id: Int) = this.find { it.id == id }

fun <T : Entity> MutableList<T>.delete(entity: T) {
    var foundIndex: Int? = null
    for ((index, item) in this.withIndex())
        if (item.id == entity.id)
            foundIndex = index
    foundIndex?.also { this.removeAt(foundIndex) }
}

fun <T : Entity> MutableList<T>.saveChanges(entity: T) {
    var foundIndex: Int? = null
    for ((index, item) in this.withIndex())
        if (item.id == entity.id)
            foundIndex = index
    foundIndex?.also { this[foundIndex] = entity }
}

fun <T : User> MutableList<T>.loginExists(login: String) = this.any { it.login == login }