package ru.mmishak.bicyclewalks.domain.entities.users.implementation

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity

data class Leader(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val firstName: String,
        override val secondName: String,
        override val phone: String
) : LeaderEntity {

    override fun acceptWalkRequest(walk: BicycleWalkEntity) {
        walk.leader ?: return
        if (walk.leader == this && walk.leaderStatus == LeaderStatus.WAITING_ACCEPT)
            walk.leaderStatus = LeaderStatus.ACCEPTED
    }

    override fun rejectWalkRequest(walk: BicycleWalkEntity) {
        walk.leader ?: return
        if (walk.leader == this && walk.leaderStatus == LeaderStatus.WAITING_ACCEPT)
            walk.leaderStatus = LeaderStatus.REJECTED
    }
}