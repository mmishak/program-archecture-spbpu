package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.review.implementation.TextReview
import ru.mmishak.bicyclewalks.domain.entities.users.base.HumanEntity
import ru.mmishak.bicyclewalks.data.repositories.base.ReviewRepository

class MockedReviewRepository : ReviewRepository {
    override fun generateId() = DataBaseImitator.reviews.generateId()

    override fun getAll() = DataBaseImitator.reviews.toList()

    override fun get(id: Int) = DataBaseImitator.reviews.find(id)

    override fun delete(entity: ReviewEntity) = DataBaseImitator.reviews.delete(entity)

    override fun saveChanges(entity: ReviewEntity) = DataBaseImitator.reviews.saveChanges(entity)

    override fun createTextReview(author: HumanEntity, bicycleWalk: BicycleWalkEntity, time: Long, text: String): TextReview {
        val review = TextReview(
                id = generateId(),
                author = author,
                bicycleWalk = bicycleWalk,
                time = time,
                text = text
        )
        DataBaseImitator.reviews.add(review)
        return review
    }
}
