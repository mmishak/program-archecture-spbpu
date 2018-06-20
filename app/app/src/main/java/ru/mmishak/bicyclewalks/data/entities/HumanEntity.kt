package ru.mmishak.bicyclewalks.data.entities

data class HumanEntity(
        override val id: Int,
        val login: String,
        val password: String,
        val email: String,
        val firstName: String,
        val secondName: String,
        val phone: String
) : Entity