package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.repositories.base.BicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.util.DateTimeHelper

class MockedBicycleWalkRepository : BicycleWalkRepository {
    override fun generateId() = DataBaseImitator.bicycleWalks.generateId()

    override fun getAll() = DataBaseImitator.bicycleWalks.toList()

    override fun get(id: Int) = DataBaseImitator.bicycleWalks.find(id)

    override fun delete(entity: BicycleWalkEntity) = DataBaseImitator.bicycleWalks.delete(entity)

    override fun saveChanges(entity: BicycleWalkEntity) = DataBaseImitator.bicycleWalks.saveChanges(entity)

    override fun getAllForOrganizer(organizer: OrganizerEntity) = DataBaseImitator.bicycleWalks.filter { it.organizer.id == organizer.id }

    override fun getAllForLeader(leader: LeaderEntity) = DataBaseImitator.bicycleWalks.filter { it.leader?.id == leader.id }

    override fun getAllForCyclist(cyclist: CyclistEntity) = DataBaseImitator.bicycleWalks.filter { it.cyclists.any { it.id == cyclist.id } }

    override fun getAllAccepted() = DataBaseImitator.bicycleWalks.filter { it.leaderStatus == LeaderStatus.WITHOUT_LEADER || it.leaderStatus == LeaderStatus.ACCEPTED }

    override fun create(title: String, description: String, walkType: WalkType, duration: Long,
                        distance: Int, date: Long, price: Int, paymentType: PaymentType,
                        organizer: OrganizerEntity, cyclists: MutableList<CyclistEntity>, reviews: MutableList<ReviewEntity>,
                        leader: LeaderEntity?, leaderStatus: LeaderStatus?): BicycleWalkEntity {
        val walk = BicycleWalk(
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

    override fun search(walkType: WalkType?, maxDuration: Long?, maxDistance: Int?, date: Long?, maxPrice: Int?, paymentType: PaymentType?): List<BicycleWalkEntity> {
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
