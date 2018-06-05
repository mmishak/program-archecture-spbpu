package ru.mmishak.bicyclewalks.domain.entities.review

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.users.Human

data class TextReview(
        override val id: Int,
        override val author: Human,
        override val bicycleWalk: BicycleWalk,
        override val time: Long,
        val text: String
) : BaseReview(id, author, bicycleWalk, time)