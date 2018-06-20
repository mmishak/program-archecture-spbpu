package ru.mmishak.bicyclewalks.domain.repositories.implementation

import ru.mmishak.bicyclewalks.data.database.gateways.OrganizerGateway
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer
import ru.mmishak.bicyclewalks.domain.mappers.OrganizerMapper
import ru.mmishak.bicyclewalks.data.entities.OrganizerEntity as DataOrganizerEntity
import ru.mmishak.bicyclewalks.domain.repositories.base.OrganizerRepository

class OrganizerRepository : OrganizerRepository {
    override fun create(login: String, password: String, email: String, title: String): OrganizerEntity {
        val id = OrganizerGateway.create(DataOrganizerEntity(
                id = 0, login = login, password = password, email = email, title = title
        ))
        return Organizer(id = id, login = login, password = password, email = email, title = title)
    }

    override fun generateId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<OrganizerEntity> {
        return OrganizerMapper.transform(OrganizerGateway.getAll()).toList()
    }

    override fun get(id: Int): OrganizerEntity? {
        return OrganizerGateway.read(id)?.let { entity -> OrganizerMapper.transform(entity) }
    }

    override fun delete(entity: OrganizerEntity) {
        OrganizerGateway.delete(entity.id)
    }

    override fun saveChanges(entity: OrganizerEntity) {
        entity.run {
            OrganizerGateway.update(DataOrganizerEntity(
                    id = id, login = login, password = password, email = email, title = title
            ))
        }
    }
}