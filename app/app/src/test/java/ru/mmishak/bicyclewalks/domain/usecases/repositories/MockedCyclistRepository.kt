package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Cyclist
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.CyclistRepository

class MockedCyclistRepository : CyclistRepository {

    override fun generateId() = DataBaseImitator.cyclists.generateId()

    override fun getAll(callback: (isSuccess: Boolean, entities: List<CyclistEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.cyclists.toList())
    }

    override fun get(id: Int, callback: (entity: CyclistEntity?) -> Unit) {
        callback.invoke(DataBaseImitator.cyclists.find(id))
    }

    override fun delete(entity: CyclistEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.cyclists.delete(entity)
        callback?.invoke(isSuccess)
    }

    override fun saveChanges(entity: CyclistEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.cyclists.saveChanges(entity)
        callback?.invoke(isSuccess)
    }

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, firstName: String,
                        secondName: String, phone: String, callback: ((entity: CyclistEntity?) -> Unit)?) {
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
        val isSuccess = DataBaseImitator.cyclists.add(cyclist)
        callback?.invoke(if (isSuccess) cyclist else null)
    }
}
