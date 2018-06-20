package ru.mmishak.bicyclewalks.domain.entities.users.implementation

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.repositories.implementation.BicycleWalkRepository

data class Organizer(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val title: String
) : OrganizerEntity {
    override fun createWalk(title: String, description: String, walkType: WalkType, duration: Long, distance: Int, date: Long, price: Int, leader: LeaderEntity?): BicycleWalkEntity {
        return BicycleWalkRepository().create(
                title = title,
                description = description,
                walkType = walkType,
                duration = duration,
                distance = distance,
                date = date,
                paymentType = if (price == 0) PaymentType.FREE else PaymentType.PAY,
                organizer = this,
                leader = leader,
                leaderStatus = if (leader == null) LeaderStatus.WITHOUT_LEADER else LeaderStatus.WAITING_ACCEPT
        )
    }
}