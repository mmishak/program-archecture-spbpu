package ru.mmishak.bicyclewalks.domain.mappers

import ru.mmishak.bicyclewalks.data.entities.Entity as DataEntity
import ru.mmishak.bicyclewalks.domain.entities.Entity as DomainEntity

interface DataMapper<T : DataEntity, E : DomainEntity> {
    fun transform(entity: T): E

    fun transform(collection: Collection<T>): Collection<E>
}