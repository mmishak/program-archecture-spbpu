package ru.mmishak.bicyclewalks.domain.entities.users

interface Human : User {
    val firstName: String
    val secondName: String
    val phone: String
}