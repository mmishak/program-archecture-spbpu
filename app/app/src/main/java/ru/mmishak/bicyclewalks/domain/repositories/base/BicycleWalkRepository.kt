package ru.mmishak.bicyclewalks.domain.repositories.base

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.users.*

interface BicycleWalkRepository : BaseRepository<BicycleWalk> {
    fun getAllForOrganizer(organizer: Organizer): List<BicycleWalk>

    fun getAllForLeader(leader: Leader): List<BicycleWalk>

    fun getAllForCyclist(cyclist: Cyclist): List<BicycleWalk>

    fun getAllAccepted(): List<BicycleWalk>

    fun create(title: String, description: String, walkType: WalkType, duration: Long,
               distance: Int, date: Long, price: Int = 0, paymentType: PaymentType = PaymentType.FREE, organizer: Organizer, cyclists: MutableList<Cyclist> = mutableListOf(),
               reviews: MutableList<Review> = mutableListOf(), leader: Leader? = null, leaderStatus: LeaderStatus? = null): BicycleWalk

    fun search(walkType: WalkType? = null, maxDuration: Long? = null, maxDistance: Int? = null,
               date: Long? = null, maxPrice: Int? = null, paymentType: PaymentType? = null): List<BicycleWalk>
}