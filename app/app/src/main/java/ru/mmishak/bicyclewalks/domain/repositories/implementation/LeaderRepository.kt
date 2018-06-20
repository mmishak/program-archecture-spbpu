package ru.mmishak.bicyclewalks.domain.repositories.implementation

import ru.mmishak.bicyclewalks.data.database.gateways.LeaderGateway
import ru.mmishak.bicyclewalks.data.entities.HumanEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader
import ru.mmishak.bicyclewalks.domain.mappers.LeaderMapper
import ru.mmishak.bicyclewalks.domain.repositories.base.LeaderRepository

class LeaderRepository : LeaderRepository {
    override fun create(login: String, password: String, email: String, firstName: String, secondName: String, phone: String): LeaderEntity {
        val id = LeaderGateway.create(HumanEntity(
                id = 0, login = login, password = password, email = email, firstName = firstName, secondName = secondName, phone = phone
        ))
        return Leader(id = id, login = login, password = password, email = email, firstName = firstName, secondName = secondName, phone = phone)
    }

    override fun generateId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<LeaderEntity> {
        return LeaderMapper.transform(LeaderGateway.getAll()).toList()
    }

    override fun get(id: Int): LeaderEntity? {
        return LeaderGateway.read(id)?.let { entity -> LeaderMapper.transform(entity) }
    }

    override fun delete(entity: LeaderEntity) {
        LeaderGateway.delete(entity.id)
    }

    override fun saveChanges(entity: LeaderEntity) {
        entity.run {
            LeaderGateway.update(HumanEntity(
                    id = id, login = login, password = password, email = email, firstName = firstName, secondName = secondName, phone = phone
            ))
        }
    }
}