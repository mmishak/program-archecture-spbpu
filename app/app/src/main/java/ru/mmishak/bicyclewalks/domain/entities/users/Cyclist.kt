package ru.mmishak.bicyclewalks.domain.entities.users

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk

interface Cyclist : Human {
    fun registerToWalk(walk: BicycleWalk, callback: ((isSuccess: Boolean, walk: BicycleWalk) -> Unit)? = null)
}