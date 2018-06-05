package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.BaseCyclist
import ru.mmishak.bicyclewalks.domain.entities.users.Cyclist
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.CyclistRepository

class MockedCyclistRepository : CyclistRepository {

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, firstName: String, secondName: String, phone: String): Cyclist {
        if (DataBaseImitator.loginExists(login)) throw LoginAlreadyExistsException(login)
        val cyclist = BaseCyclist(
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

    override fun generateId() = DataBaseImitator.cyclists.generateId()

    override fun getAll() = DataBaseImitator.cyclists.toList()

    override fun get(id: Int) = DataBaseImitator.cyclists.find(id)

    override fun delete(entity: Cyclist) = DataBaseImitator.cyclists.delete(entity)

    override fun saveChanges(entity: Cyclist) = DataBaseImitator.cyclists.saveChanges(entity)
}