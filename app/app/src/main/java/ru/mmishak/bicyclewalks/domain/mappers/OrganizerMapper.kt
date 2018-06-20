package ru.mmishak.bicyclewalks.domain.mappers

import ru.mmishak.bicyclewalks.data.entities.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer

object OrganizerMapper : DataMapper<OrganizerEntity, Organizer> {
    override fun transform(entity: OrganizerEntity): Organizer {
        return Organizer(
                id = entity.id,
                login = entity.login,
                password = entity.password,
                email = entity.email,
                title = entity.title
        )
    }

    override fun transform(collection: Collection<OrganizerEntity>): Collection<Organizer> {
        return collection.map { transform(it) }
    }
}