package ru.mmishak.bicyclewalks.domain.entities.users.base

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity

interface OrganizerEntity : UserEntity {
    val title: String

    fun createWalk(title: String, description: String, walkType: WalkType, duration: Long, distance: Int, date: Long, price: Int, leader: LeaderEntity? = null): BicycleWalkEntity
}