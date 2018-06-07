package ru.mmishak.bicyclewalks.data.mapper

import ru.mmishak.bicyclewalks.data.entities.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.review.implementation.TextReview

object ReviewDataMapper : Mapper<ReviewEntity, TextReview> {
    override fun transform(entity: ReviewEntity): TextReview {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transform(collection: List<ReviewEntity>): List<TextReview> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}