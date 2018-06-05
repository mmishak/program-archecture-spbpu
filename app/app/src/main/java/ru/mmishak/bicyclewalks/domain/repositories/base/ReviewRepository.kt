package ru.mmishak.bicyclewalks.domain.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.review.implementation.TextReview
import ru.mmishak.bicyclewalks.domain.entities.users.base.HumanEntity

interface ReviewRepository : BaseRepository<ReviewEntity> {
    fun createTextReview(author: HumanEntity, bicycleWalk: BicycleWalkEntity, time: Long, text: String,
                         callback: ((review: TextReview?) -> Unit)? = null)
}