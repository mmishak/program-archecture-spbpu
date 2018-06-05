package ru.mmishak.bicyclewalks.domain.entities.bicyclewalk

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.users.*

data class BaseBicycleWalk(
        override val id: Int,
        override var title: String,
        override var description: String,
        override val walkType: WalkType,
        override var duration: Long,
        override var distance: Int,
        override val date: Long,
        override val price: Int,
        override val paymentType: PaymentType,
        override val organizer: Organizer,
        override val cyclists: MutableList<Cyclist>,
        override val reviews: MutableList<Review>,
        override var leader: Leader? = null,
        override var leaderStatus: LeaderStatus = if (leader == null) LeaderStatus.WITHOUT_LEADER else LeaderStatus.WAITING_ACCEPT
) : BicycleWalk {
    override fun isPublished() = leaderStatus == LeaderStatus.WITHOUT_LEADER || leaderStatus == LeaderStatus.ACCEPTED
}