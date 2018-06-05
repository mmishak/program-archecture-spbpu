package ru.mmishak.bicyclewalks.domain.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.users.BaseOrganizer
import ru.mmishak.bicyclewalks.domain.entities.users.Organizer
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException

interface OrganizerRepository : BaseRepository<Organizer> {
    @Throws(LoginAlreadyExistsException::class)
    fun create(login: String, password: String, email: String, title: String): Organizer
}