package ru.mmishak.bicyclewalks.data.mapper

import ru.mmishak.bicyclewalks.data.entities.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer

object OrganizerDataMapper : Mapper<OrganizerEntity, Organizer> {
    override fun transform(entity: OrganizerEntity): Organizer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transform(collection: List<OrganizerEntity>): List<Organizer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}