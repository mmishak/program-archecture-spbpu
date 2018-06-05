package ru.mmishak.bicyclewalks.domain.entities.users.base

interface HumanEntity : UserEntity {
    val firstName: String
    val secondName: String
    val phone: String
}