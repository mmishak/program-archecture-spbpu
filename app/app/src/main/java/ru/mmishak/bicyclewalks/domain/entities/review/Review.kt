package ru.mmishak.bicyclewalks.domain.entities.review

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.users.Human

interface Review : Entity{
    val author: Human
    val bicycleWalk: BicycleWalk
    val time: Long
}