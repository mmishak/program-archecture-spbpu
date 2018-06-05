package ru.mmishak.bicyclewalks.domain.entities.users

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus

data class BaseLeader(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val firstName: String,
        override val secondName: String,
        override val phone: String
) : Leader {

    override fun acceptWalkRequest(walk: BicycleWalk) {
        walk.leader ?: return
        if (walk.leader == this && walk.leaderStatus == LeaderStatus.WAITING_ACCEPT)
            walk.leaderStatus = LeaderStatus.ACCEPTED
    }

    override fun rejectWalkRequest(walk: BicycleWalk) {
        walk.leader ?: return
        if (walk.leader == this && walk.leaderStatus == LeaderStatus.WAITING_ACCEPT)
            walk.leaderStatus = LeaderStatus.REJECTED
    }
}