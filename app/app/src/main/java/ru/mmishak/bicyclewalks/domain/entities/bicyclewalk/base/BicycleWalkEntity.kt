package ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity

interface BicycleWalkEntity : Entity {
    var title: String
    var description: String
    val walkType: WalkType
    var duration: Long
    var distance: Int
    val date: Long
    val price: Int
    val paymentType: PaymentType
    val organizer: OrganizerEntity
    val cyclists: MutableList<CyclistEntity>
    val reviews: MutableList<ReviewEntity>
    var leader: LeaderEntity?
    var leaderStatus: LeaderStatus

    fun isPublished(): Boolean
}