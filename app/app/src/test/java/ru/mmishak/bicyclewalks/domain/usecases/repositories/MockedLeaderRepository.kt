package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.BaseLeader
import ru.mmishak.bicyclewalks.domain.entities.users.Leader
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.LeaderRepository

class MockedLeaderRepository : LeaderRepository {

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, firstName: String, secondName: String, phone: String): Leader {
        if (DataBaseImitator.loginExists(login)) throw LoginAlreadyExistsException(login)
        val leader = BaseLeader(
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

    override fun generateId() = DataBaseImitator.leaders.generateId()

    override fun getAll() = DataBaseImitator.leaders.toList()

    override fun get(id: Int) = DataBaseImitator.leaders.find(id)

    override fun delete(entity: Leader) = DataBaseImitator.leaders.delete(entity)

    override fun saveChanges(entity: Leader) = DataBaseImitator.leaders.saveChanges(entity)

}