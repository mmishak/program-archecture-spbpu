package ru.mmishak.bicyclewalks.domain.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.review.TextReview
import ru.mmishak.bicyclewalks.domain.entities.users.Human

interface ReviewRepository : BaseRepository<Review> {
    fun createTextReview(author: Human, bicycleWalk: BicycleWalk, time: Long, text: String): TextReview
}