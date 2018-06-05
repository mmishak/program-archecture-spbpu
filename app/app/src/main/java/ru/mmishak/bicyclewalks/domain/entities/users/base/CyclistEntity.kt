package ru.mmishak.bicyclewalks.domain.entities.users.base

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity

interface CyclistEntity : HumanEntity {
    fun registerToWalk(walk: BicycleWalkEntity, callback: ((isSuccess: Boolean, walk: BicycleWalkEntity) -> Unit)? = null)

    fun addReview(walk: BicycleWalkEntity)
}