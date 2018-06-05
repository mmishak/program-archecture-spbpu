package ru.mmishak.bicyclewalks.domain.entities.users

data class BaseOrganizer(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val title: String
) : Organizer