package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BaseBicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.users.*
import ru.mmishak.bicyclewalks.domain.repositories.base.BicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.util.DateTimeHelper

class MockedBicycleWalkRepository : BicycleWalkRepository {
    override fun generateId() = DataBaseImitator.bicycleWalks.generateId()

    override fun getAll() = DataBaseImitator.bicycleWalks.toList()

    override fun get(id: Int) = DataBaseImitator.bicycleWalks.find(id)

    override fun delete(entity: BicycleWalk) = DataBaseImitator.bicycleWalks.delete(entity)

    override fun saveChanges(entity: BicycleWalk) = DataBaseImitator.bicycleWalks.saveChanges(entity)

    override fun getAllForOrganizer(organizer: Organizer) = DataBaseImitator.bicycleWalks.filter { it.organizer.id == organizer.id }

    override fun getAllForLeader(leader: Leader) = DataBaseImitator.bicycleWalks.filter { it.leader?.id == leader.id }

    override fun getAllForCyclist(cyclist: Cyclist) = DataBaseImitator.bicycleWalks.filter { it.cyclists.any { it.id == cyclist.id } }

    override fun getAllAccepted() = DataBaseImitator.bicycleWalks.filter { it.leaderStatus == LeaderStatus.WITHOUT_LEADER || it.leaderStatus == LeaderStatus.ACCEPTED }

    override fun create(title: String, description: String, walkType: WalkType, duration: Long,
                        distance: Int, date: Long, price: Int, paymentType: PaymentType,
                        organizer: Organizer, cyclists: MutableList<Cyclist>, reviews: MutableList<Review>,
                        leader: Leader?, leaderStatus: LeaderStatus?): BicycleWalk {
        val walk = BaseBicycleWalk(
                id = generateId(),
                title = title,
                description = description,
                walkType = walkType,
                duration = duration,
                distance = distance,
                date = date,
                price = price,
                paymentType = paymentType,
                organizer = organizer,
                cyclists = cyclists,
                reviews = reviews,
                leader = leader
        )
        DataBaseImitator.bicycleWalks.add(walk)
        return walk
    }

    override fun search(walkType: WalkType?, maxDuration: Long?, maxDistance: Int?, date: Long?, maxPrice: Int?, paymentType: PaymentType?): List<BicycleWalk> {
        return DataBaseImitator.bicycleWalks.filter { walk ->
            walkType?.let { walk.walkType == it } ?: true &&
                    maxDuration?.let { walk.duration <= it } ?: true &&
                    maxDistance?.let { walk.distance <= it } ?: true &&
                    date?.let { DateTimeHelper.equalsDates(walk.date, it) } ?: true &&
                    maxPrice?.let { walk.price <= it } ?: true &&
                    paymentType?.let { walk.paymentType == it } ?: true
        }
    }
}
