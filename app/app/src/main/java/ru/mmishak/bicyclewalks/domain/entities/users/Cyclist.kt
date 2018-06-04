package ru.mmishak.bicyclewalks.domain.entities.users

class Cyclist(
        id: Int,
        login: String,
        password: String,
        email: String,
        firstName: String,
        secondName: String,
        phone: String
) : Human(id, login, password, email, firstName, secondName, phone)