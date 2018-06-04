package ru.mmishak.bicyclewalks.domain.entities.users

class Organizer(
        id: Int,
        login: String,
        password: String,
        email: String,
        val title: String
) : BaseUser(id, login, password, email)