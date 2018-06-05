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

    override fun getAll(callback: (isSuccess: Boolean, entities: List<BicycleWalkEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.bicycleWalks.toList())
    }

    override fun get(id: Int, callback: (entity: BicycleWalkEntity?) -> Unit) {
        callback.invoke(DataBaseImitator.bicycleWalks.find(id))
    }

    override fun delete(entity: BicycleWalkEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.bicycleWalks.delete(entity)
        callback?.invoke(isSuccess)
    }

    override fun saveChanges(entity: BicycleWalkEntity, callback: ((isSuccess: Boolean) -> Unit)?) {
        val isSuccess = DataBaseImitator.bicycleWalks.saveChanges(entity)
        callback?.invoke(isSuccess)
    }

    override fun getAllForOrganizer(organizer: OrganizerEntity, callback: (isSuccess: Boolean, walks: List<BicycleWalkEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.bicycleWalks.filter { it.organizer.id == organizer.id })
    }

    override fun getAllForLeader(leader: LeaderEntity, callback: (isSuccess: Boolean, walks: List<BicycleWalkEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.bicycleWalks.filter { it.leader?.id == leader.id })
    }

    override fun getAllForCyclist(cyclist: CyclistEntity, callback: (isSuccess: Boolean, walks: List<BicycleWalkEntity>) -> Unit) {
        callback.invoke(true, DataBaseImitator.bicycleWalks.filter { it.cyclists.any { it.id == cyclist.id } })
    }

    override fun getAllAccepted(callback: (isSuccess: Boolean, walks: List<BicycleWalkEntity>) -> Unit) {
        callback.invoke(
                true,
                DataBaseImitator.bicycleWalks.filter { it.leaderStatus == LeaderStatus.WITHOUT_LEADER || it.leaderStatus == LeaderStatus.ACCEPTED }
        )
    }

    override fun create(title: String, description: String, walkType: WalkType, duration: Long,
                        distance: Int, date: Long, price: Int, paymentType: PaymentType,
                        organizer: OrganizerEntity, cyclists: MutableList<CyclistEntity>, reviews: MutableList<ReviewEntity>,
                        leader: LeaderEntity?, leaderStatus: LeaderStatus?, callback: ((walk: BicycleWalkEntity?) -> Unit)?) {
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
        val isSuccess = DataBaseImitator.bicycleWalks.add(walk)
        callback?.invoke(if (isSuccess) walk else null)
    }

    override fun search(walkType: WalkType?, maxDuration: Long?, maxDistance: Int?, date: Long?,
                        maxPrice: Int?, paymentType: PaymentType?,
                        callback: (isSuccess: Boolean, walks: List<BicycleWalkEntity>) -> Unit) {
        callback.invoke(
                true,
                DataBaseImitator.bicycleWalks.filter { walk ->
                    walkType?.let { walk.walkType == it } ?: true &&
                            maxDuration?.let { walk.duration <= it } ?: true &&
                            maxDistance?.let { walk.distance <= it } ?: true &&
                            date?.let { DateTimeHelper.equalsDates(walk.date, it) } ?: true &&
                            maxPrice?.let { walk.price <= it } ?: true &&
                            paymentType?.let { walk.paymentType == it } ?: true
                }
        )
    }
}
