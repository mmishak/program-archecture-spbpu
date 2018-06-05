package ru.mmishak.bicyclewalks.domain.entities.review.implementation

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.HumanEntity

data class TextReview(
        override val id: Int,
        override val author: HumanEntity,
        override val bicycleWalk: BicycleWalkEntity,
        override val time: Long,
        val text: String
) : ReviewEntity