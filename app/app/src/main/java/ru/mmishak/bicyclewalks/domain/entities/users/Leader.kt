package ru.mmishak.bicyclewalks.domain.entities.users

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk

interface Leader : Human {
    fun acceptWalkRequest(walk: BicycleWalk)

    fun rejectWalkRequest(walk: BicycleWalk)
}