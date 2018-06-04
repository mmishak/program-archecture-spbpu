package ru.mmishak.bicyclewalks.domain.entities.bicyclewalk

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.users.Cyclist
import ru.mmishak.bicyclewalks.domain.entities.users.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.Organizer

interface BicycleWalk : Entity {
    val title: String
    val description: String
    val walkType: WalkType
    val duration: Long
    val distance: Int
    val date: Long
    val price: Int
    val paymentType: PaymentType
    val organizer: Organizer
    val cyclists: List<Cyclist>
    val reviews: List<Review>
    var leader: Leader?
    var leaderStatus: LeaderStatus
}