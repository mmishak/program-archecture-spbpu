package ru.mmishak.bicyclewalks.domain.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.users.base.HumanEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException

interface HumanRepository<T : HumanEntity> : BaseRepository<T> {
    @Throws(LoginAlreadyExistsException::class)
    fun create(login: String, password: String, email: String, firstName: String, secondName: String, phone: String): T
}