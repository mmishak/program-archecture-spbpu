package ru.mmishak.bicyclewalks.domain.mappers

import ru.mmishak.bicyclewalks.data.entities.HumanEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader

object LeaderMapper : DataMapper<HumanEntity, LeaderEntity> {
    override fun transform(entity: HumanEntity): LeaderEntity {
        return Leader(
                id = entity.id,
                login = entity.login,
                password = entity.password,
                email = entity.email,
                firstName = entity.firstName,
                secondName = entity.secondName,
                phone = entity.phone
        )
    }

    override fun transform(collection: Collection<HumanEntity>): Collection<LeaderEntity> {
        return collection.map { transform(it) }
    }
}