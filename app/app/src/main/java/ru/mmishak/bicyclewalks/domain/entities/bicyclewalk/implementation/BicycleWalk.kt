package ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity

data class BicycleWalk(
        override val id: Int,
        override var title: String,
        override var description: String,
        override val walkType: WalkType,
        override var duration: Long,
        override var distance: Int,
        override val date: Long,
        override val price: Int,
        override val paymentType: PaymentType,
        override val organizer: OrganizerEntity,
        override val cyclists: MutableList<CyclistEntity>,
        override val reviews: MutableList<ReviewEntity>,
        override var leader: LeaderEntity? = null,
        override var leaderStatus: LeaderStatus = if (leader == null) LeaderStatus.WITHOUT_LEADER else LeaderStatus.WAITING_ACCEPT
) : BicycleWalkEntity {
    override fun isPublished() = leaderStatus == LeaderStatus.WITHOUT_LEADER || leaderStatus == LeaderStatus.ACCEPTED
}