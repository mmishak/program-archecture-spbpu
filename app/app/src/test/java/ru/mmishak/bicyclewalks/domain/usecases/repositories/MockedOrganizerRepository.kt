package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.users.BaseOrganizer
import ru.mmishak.bicyclewalks.domain.entities.users.Organizer
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException
import ru.mmishak.bicyclewalks.domain.repositories.base.OrganizerRepository

class MockedOrganizerRepository : OrganizerRepository {

    @Throws(LoginAlreadyExistsException::class)
    override fun create(login: String, password: String, email: String, title: String): Organizer {
        if (DataBaseImitator.loginExists(login)) throw LoginAlreadyExistsException(login)
        val organizer = BaseOrganizer(
                id = generateId(),
                login = login,
                password = password,
                email = email,
                title = title
        )
        DataBaseImitator.organizers.add(organizer)
        return organizer
    }

    override fun generateId() = DataBaseImitator.organizers.generateId()

    override fun getAll() = DataBaseImitator.organizers.toList()

    override fun get(id: Int) = DataBaseImitator.organizers.find(id)

    override fun delete(entity: Organizer) = DataBaseImitator.organizers.delete(entity)

    override fun saveChanges(entity: Organizer) = DataBaseImitator.organizers.saveChanges(entity)
}