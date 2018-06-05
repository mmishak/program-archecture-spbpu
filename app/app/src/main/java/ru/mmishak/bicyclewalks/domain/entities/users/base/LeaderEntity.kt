package ru.mmishak.bicyclewalks.domain.entities.users.base

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity

interface LeaderEntity : HumanEntity {
    fun acceptWalkRequest(walk: BicycleWalkEntity)

    fun rejectWalkRequest(walk: BicycleWalkEntity)
}