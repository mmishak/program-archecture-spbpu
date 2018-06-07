package ru.mmishak.bicyclewalks.data.mapper

interface Mapper<T, E> {
    fun transform(entity: T): E

    fun transform(collection: List<T>): List<E>
}