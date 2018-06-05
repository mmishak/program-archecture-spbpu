package ru.mmishak.bicyclewalks.domain.entities.users.implementation

import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity

data class Organizer(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val title: String
) : OrganizerEntity