package ru.mmishak.bicyclewalks

import ru.mmishak.bicyclewalks.domain.entities.users.base.UserEntity
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.implementation.Organizer

object Config {
    var currentUser: UserEntity? = null

    val isLeaderCurrent: Boolean
        get() = currentUser?.let { it is Leader } ?: false

    val isOrganizerCurrent: Boolean
        get() = currentUser?.let { it is Organizer } ?: false

    val isLogin: Boolean
        get() = currentUser != null
}