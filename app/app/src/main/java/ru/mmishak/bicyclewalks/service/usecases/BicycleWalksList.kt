package ru.mmishak.bicyclewalks.service.usecases

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.repositories.implementation.BicycleWalkRepository

object BicycleWalksList {
    fun getOrganizerWalks(organizer: OrganizerEntity): List<BicycleWalkEntity> {
        return BicycleWalkRepository().getAllForOrganizer(organizer)
    }

    fun getLeaderWalks(leader: LeaderEntity): List<BicycleWalkEntity> {
        return BicycleWalkRepository().getAllForLeader(leader)
    }
}