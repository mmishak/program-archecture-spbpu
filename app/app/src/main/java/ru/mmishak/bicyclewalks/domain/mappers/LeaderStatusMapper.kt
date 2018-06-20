package ru.mmishak.bicyclewalks.domain.mappers

import ru.mmishak.bicyclewalks.data.database.tables.BicycleWalkTable
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus

object LeaderStatusMapper {
    fun toLeaderStatus(status: Int): LeaderStatus {
        return when (status) {
            BicycleWalkTable.STATUS_ACCEPTED -> LeaderStatus.ACCEPTED
            BicycleWalkTable.STATUS_WITHOUT_LEADER -> LeaderStatus.WITHOUT_LEADER
            BicycleWalkTable.STATUS_WAITING_ACCEPT -> LeaderStatus.WAITING_ACCEPT
            BicycleWalkTable.STATUS_REJECTED -> LeaderStatus.REJECTED
            else -> LeaderStatus.REJECTED
        }
    }

    fun fromLeaderStatus(leaderStatus: LeaderStatus): Int {
        return when (leaderStatus) {
            LeaderStatus.WITHOUT_LEADER -> BicycleWalkTable.STATUS_WITHOUT_LEADER
            LeaderStatus.WAITING_ACCEPT -> BicycleWalkTable.STATUS_WAITING_ACCEPT
            LeaderStatus.ACCEPTED -> BicycleWalkTable.STATUS_ACCEPTED
            LeaderStatus.REJECTED -> BicycleWalkTable.STATUS_REJECTED
        }

    }
}