package ru.mmishak.bicyclewalks.domain.entities.users.base

import ru.mmishak.bicyclewalks.domain.entities.Entity

interface UserEntity : Entity {
    val login: String
    val password: String
    val email: String
}