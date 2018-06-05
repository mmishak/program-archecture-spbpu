package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.OrganizerRepository

class MockedOrganizerRepository : OrganizerRepository {

    override fun generateId() = DataBaseImitator.organizers.generateId()

    override fun getAll(callback: (isSuccess: Boolean, entities: List<OrganizerEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.organizers.toList())
    }

    override fun get(id: Int, callback: (entity: OrganizerEntity?) -> Unit) {
        callback.invoke(DataBaseImitator.organizers.find(id))
    }

    override fun delete(entity: OrganizerEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.organizers.delete(entity)
        callback?.invoke(isSuccess)
    }

    override fun saveChanges(entity: OrganizerEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.organizers.saveChanges(entity)
        callback?.invoke(isSuccess)
    }

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, title: String,
                        callback: ((organizer: OrganizerEntity?) -> Unit)?) {
        if (DataBaseImitator.loginExists(login)) throw LoginAlreadyExistsException(login)
        val organizer = Organizer(
                id = generateId(),
                login = login,
                password = password,
                email = email,
                title = title
        )
        val isSuccess = DataBaseImitator.organizers.add(organizer)
        callback?.invoke(if (isSuccess) organizer else null)
    }
}
