package ru.mmishak.bicyclewalks.domain.entities.users

open class Human(
        id: Int,
        login: String,
        password: String,
        email: String,
        val firstName: String,
        val secondName: String,
        val phone: String
) : BaseUser(id, login, password, email)