package ru.mmishak.bicyclewalks.data.entities

data class OrganizerEntity(
        val id: Int,
        val login: String,
        val password: String,
        val email: String,
        val title: String
)