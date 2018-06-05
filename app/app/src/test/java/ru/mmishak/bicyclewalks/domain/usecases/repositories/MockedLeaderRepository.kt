package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.LeaderRepository

class MockedLeaderRepository : LeaderRepository {

    override fun generateId() = DataBaseImitator.leaders.generateId()

    override fun getAll(callback: (isSuccess: Boolean, entities: List<LeaderEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.leaders.toList())
    }

    override fun get(id: Int, callback: (entity: LeaderEntity?) -> Unit) {
        callback.invoke(DataBaseImitator.leaders.find(id))
    }

    override fun delete(entity: LeaderEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.leaders.delete(entity)
        callback?.invoke(isSuccess)
    }

    override fun saveChanges(entity: LeaderEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.leaders.saveChanges(entity)
        callback?.invoke(isSuccess)
    }

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, firstName: String,
                        secondName: String, phone: String,
                        callback: ((entity: LeaderEntity?) -> Unit)?) {
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
        val isSuccess = DataBaseImitator.leaders.add(leader)
        callback?.invoke(if (isSuccess) leader else null)
    }
}