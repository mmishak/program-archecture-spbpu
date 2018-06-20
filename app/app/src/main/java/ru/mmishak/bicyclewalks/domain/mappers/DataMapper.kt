package ru.mmishak.bicyclewalks.domain.mappers

interface DataMapper<T, E> {
    fun transform(entity: T): E

    fun transform(collection: Collection<T>): Collection<E>
}