package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Cyclist
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.data.repositories.base.CyclistRepository

class MockedCyclistRepository : CyclistRepository {

    override fun generateId() = DataBaseImitator.cyclists.generateId()

    override fun getAll() = DataBaseImitator.cyclists.toList()

    override fun get(id: Int) = DataBaseImitator.cyclists.find(id)

    override fun delete(entity: CyclistEntity) = DataBaseImitator.cyclists.delete(entity)

    override fun saveChanges(entity: CyclistEntity) = DataBaseImitator.cyclists.saveChanges(entity)

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, firstName: String, secondName: String, phone: String): CyclistEntity {
        if (DataBaseImitator.loginExists(login)) throw LoginAlreadyExistsException(login)
        val cyclist = Cyclist(
                id = generateId(),
                login = login,
                password = password,
                email = email,
                firstName = firstName,
                secondName = secondName,
                phone = phone
        )
        DataBaseImitator.cyclists.add(cyclist)
        return cyclist
    }
}