package ru.mmishak.bicyclewalks.domain.entities.review

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.users.Human

interface Review : Entity{
    val author: Human
}