package ru.mmishak.bicyclewalks.domain.entities.users

import ru.mmishak.bicyclewalks.domain.entities.Entity

interface User : Entity {
    val login: String
    val password: String
    val email: String
}