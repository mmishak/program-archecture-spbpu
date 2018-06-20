package ru.mmishak.bicyclewalks.domain.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity

interface BicycleWalkRepository : BaseRepository<BicycleWalkEntity> {
    fun getAllForOrganizer(organizer: OrganizerEntity): List<BicycleWalkEntity>

    fun getAllForLeader(leader: LeaderEntity): List<BicycleWalkEntity>

    fun getAllForCyclist(cyclist: CyclistEntity): List<BicycleWalkEntity>

    fun getAllAccepted(): List<BicycleWalkEntity>

    fun create(title: String, description: String, walkType: WalkType, duration: Long,
               distance: Int, date: Long, price: Int = 0, paymentType: PaymentType = PaymentType.FREE, organizer: OrganizerEntity, cyclists: MutableList<CyclistEntity> = mutableListOf(),
               reviews: MutableList<ReviewEntity> = mutableListOf(), leader: LeaderEntity? = null, leaderStatus: LeaderStatus? = null): BicycleWalkEntity

    fun search(walkType: WalkType? = null, maxDuration: Long? = null, maxDistance: Int? = null,
               date: Long? = null, maxPrice: Int? = null, paymentType: PaymentType? = null): List<BicycleWalkEntity>
}