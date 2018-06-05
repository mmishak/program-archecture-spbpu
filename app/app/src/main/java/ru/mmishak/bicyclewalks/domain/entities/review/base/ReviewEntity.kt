package ru.mmishak.bicyclewalks.domain.entities.review.base

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.HumanEntity

interface ReviewEntity : Entity{
    val author: HumanEntity
    val bicycleWalk: BicycleWalkEntity
    val time: Long
}