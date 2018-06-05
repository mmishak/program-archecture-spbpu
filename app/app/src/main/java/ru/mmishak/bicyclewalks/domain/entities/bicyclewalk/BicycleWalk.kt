package ru.mmishak.bicyclewalks.domain.entities.bicyclewalk

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.users.*

interface BicycleWalk : Entity {
    var title: String
    var description: String
    val walkType: WalkType
    var duration: Long
    var distance: Int
    val date: Long
    val price: Int
    val paymentType: PaymentType
    val organizer: Organizer
    val cyclists: MutableList<Cyclist>
    val reviews: MutableList<Review>
    var leader: Leader?
    var leaderStatus: LeaderStatus

    fun isPublished(): Boolean
}