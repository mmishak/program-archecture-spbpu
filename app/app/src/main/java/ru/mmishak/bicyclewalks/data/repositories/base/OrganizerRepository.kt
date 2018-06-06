package ru.mmishak.bicyclewalks.data.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException

interface OrganizerRepository : BaseRepository<OrganizerEntity> {
    @Throws(LoginAlreadyExistsException::class)
    fun create(login: String, password: String, email: String, title: String): OrganizerEntity
}