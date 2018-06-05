package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.review.implementation.TextReview
import ru.mmishak.bicyclewalks.domain.entities.users.base.HumanEntity
import ru.mmishak.bicyclewalks.domain.repositories.base.ReviewRepository

class MockedReviewRepository : ReviewRepository {
    override fun generateId() = DataBaseImitator.reviews.generateId()

    override fun getAll(callback: (isSuccess: Boolean, entities: List<ReviewEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.reviews.toList())
    }

    override fun get(id: Int, callback: (entity: ReviewEntity?) -> Unit) {
        callback.invoke(DataBaseImitator.reviews.find(id))
    }

    override fun delete(entity: ReviewEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.reviews.delete(entity)
        callback?.invoke(isSuccess)
    }

    override fun saveChanges(entity: ReviewEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.reviews.saveChanges(entity)
        callback?.invoke(isSuccess)
    }

    override fun createTextReview(author: HumanEntity, bicycleWalk: BicycleWalkEntity, time: Long,
                                  text: String, callback: ((review: TextReview?) -> Unit)?) {
        val review = TextReview(
                id = generateId(),
                author = author,
                bicycleWalk = bicycleWalk,
                time = time,
                text = text
        )
        val isSuccess = DataBaseImitator.reviews.add(review)
        callback?.invoke(if (isSuccess) review else null)
    }
}
