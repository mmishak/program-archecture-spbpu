package ru.mmishak.bicyclewalks.domain.entities.review

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.users.Human

abstract class BaseReview(
        override val id: Int,
        override val author: Human,
        override val bicycleWalk: BicycleWalk,
        override val time: Long
) : Review