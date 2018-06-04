package ru.mmishak.bicyclewalks.domain.entities.users

abstract class BaseUser(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String
) : User