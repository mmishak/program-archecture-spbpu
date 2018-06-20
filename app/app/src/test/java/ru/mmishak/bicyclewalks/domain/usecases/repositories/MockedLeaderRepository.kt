package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.LeaderRepository

class MockedLeaderRepository : LeaderRepository {

    override fun generateId() = DataBaseImitator.leaders.generateId()

    override fun getAll() = DataBaseImitator.leaders.toList()

    override fun get(id: Int) = DataBaseImitator.leaders.find(id)

    override fun delete(entity: LeaderEntity) = DataBaseImitator.leaders.delete(entity)

    override fun saveChanges(entity: LeaderEntity) = DataBaseImitator.leaders.saveChanges(entity)

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, firstName: String, secondName: String, phone: String): LeaderEntity {
        if (DataBaseImitator.loginExists(login)) throw LoginAlreadyExistsException(login)
        val leader = Leader(
                id = generateId(),
                login = login,
                password = password,
                email = email,
                firstName = firstName,
                secondName = secondName,
                phone = phone
        )
        DataBaseImitator.leaders.add(leader)
        return leader
    }
}