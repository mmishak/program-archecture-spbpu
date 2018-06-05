package ru.mmishak.bicyclewalks.domain.repositories.base

import android.webkit.ValueCallback
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.exceptions.LoginAlreadyExistsException

interface OrganizerRepository : BaseRepository<OrganizerEntity> {
    @Throws(LoginAlreadyExistsException::class)
    fun create(login: String, password: String, email: String, title: String,
               callback: ((organizer: OrganizerEntity?) -> Unit)? = null)
}