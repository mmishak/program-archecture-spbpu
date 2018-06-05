package ru.mmishak.bicyclewalks.domain.entities.users

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk

data class BaseCyclist(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val firstName: String,
        override val secondName: String,
        override val phone: String
) : Cyclist {
    override fun registerToWalk(walk: BicycleWalk) {
        if (walk.isPublished())
            walk.cyclists.add(this)
    }
}