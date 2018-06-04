package ru.mmishak.bicyclewalks.domain.entities.bicyclewalk

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.users.Cyclist
import ru.mmishak.bicyclewalks.domain.entities.users.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.Organizer

abstract class BaseBicycleWalk(
        override val id: Int,
        override val title: String,
        override val description: String,
        override val walkType: WalkType,
        override val duration: Long,
        override val distance: Int,
        override val date: Long,
        override val price: Int,
        override val paymentType: PaymentType,
        override val organizer: Organizer,
        override val cyclists: List<Cyclist>,
        override val reviews: List<Review>,
        leader: Leader? = null,
        leaderStatus: LeaderStatus? = null
) : BicycleWalk {

    override var leader: Leader? = leader
        set(value) {
            leaderStatus = LeaderStatus.WAITING
            //TODO: ...
        }

    override var leaderStatus: LeaderStatus = leaderStatus ?: if (leader == null) LeaderStatus.NO_LEADER else LeaderStatus.WAITING
        set(value) {
            //TODO: ...
        }
}