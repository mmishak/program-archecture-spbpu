package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.OrganizerRepository

class MockedOrganizerRepository : OrganizerRepository {

    override fun generateId() = DataBaseImitator.organizers.generateId()

    override fun getAll() = DataBaseImitator.organizers.toList()

    override fun get(id: Int) = DataBaseImitator.organizers.find(id)

    override fun delete(entity: OrganizerEntity) = DataBaseImitator.organizers.delete(entity)

    override fun saveChanges(entity: OrganizerEntity) = DataBaseImitator.organizers.saveChanges(entity)

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, title: String): OrganizerEntity {
        if (DataBaseImitator.loginExists(login)) throw LoginAlreadyExistsException(login)
        val organizer = Organizer(
                id = generateId(),
                login = login,
                password = password,
                email = email,
                title = title
        )
        DataBaseImitator.organizers.add(organizer)
        return organizer
    }
}