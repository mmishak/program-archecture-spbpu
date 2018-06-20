package ru.mmishak.bicyclewalks.domain.repositories.implementation

import ru.mmishak.bicyclewalks.data.database.gateways.BicycleWalkGateway
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.mappers.BicycleWalkMapper
import ru.mmishak.bicyclewalks.domain.mappers.LeaderStatusMapper
import ru.mmishak.bicyclewalks.domain.mappers.PaymentTypeMapper
import ru.mmishak.bicyclewalks.domain.mappers.WalkTypeMapper
import ru.mmishak.bicyclewalks.domain.repositories.base.BicycleWalkRepository
import ru.mmishak.bicyclewalks.data.entities.BicycleWalkEntity as DataBicycleWalkEntity

class BicycleWalkRepository : BicycleWalkRepository {
    override fun getAllForOrganizer(organizer: OrganizerEntity): List<BicycleWalkEntity> {
        return getAll().filter { it.organizer.id == organizer.id }
    }

    override fun getAllForLeader(leader: LeaderEntity): List<BicycleWalkEntity> {
        return getAll().filter { it.leader?.id == leader.id }
    }

    override fun getAllForCyclist(cyclist: CyclistEntity): List<BicycleWalkEntity> {
        return getAll().filter { it.cyclists.any { it.id == cyclist.id } }
    }

    override fun getAllAccepted(): List<BicycleWalkEntity> {
        return getAll().filter { it.isPublished() }
    }

    override fun create(title: String, description: String, walkType: WalkType, duration: Long, distance: Int, date: Long, price: Int, paymentType: PaymentType, organizer: OrganizerEntity, cyclists: MutableList<CyclistEntity>, reviews: MutableList<ReviewEntity>, leader: LeaderEntity?, leaderStatus: LeaderStatus?): BicycleWalkEntity {
        val id = BicycleWalkGateway.create(DataBicycleWalkEntity(
                id = 0,
                title = title,
                description = description,
                walkType = WalkTypeMapper.fromWalkType(walkType),
                duration = duration,
                distance = distance,
                leaderStatus = LeaderStatusMapper.fromLeaderStatus(leaderStatus ?: LeaderStatus.WITHOUT_LEADER),
                organizer_id = organizer.id,
                leader_id = leader?.id,
                price = price,
                paymentType = PaymentTypeMapper.fromPaymentType(paymentType),
                date = date
        ))
        return BicycleWalk(
                id = id,
                title = title,
                description = description,
                walkType = walkType,
                duration = duration,
                distance = distance,
                leaderStatus = leaderStatus ?: LeaderStatus.WITHOUT_LEADER,
                organizer = organizer,
                leader = leader,
                price = price,
                paymentType = paymentType,
                date = date,
                cyclists = mutableListOf(),
                reviews = mutableListOf()
        )
    }

    override fun search(walkType: WalkType?, maxDuration: Long?, maxDistance: Int?, date: Long?, maxPrice: Int?, paymentType: PaymentType?): List<BicycleWalkEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generateId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<BicycleWalkEntity> {
        return BicycleWalkMapper.transform(BicycleWalkGateway.getAll()).toList()
    }

    override fun get(id: Int): BicycleWalkEntity? {
        return BicycleWalkGateway.read(id)?.let { entity -> BicycleWalkMapper.transform(entity) }
    }

    override fun delete(entity: BicycleWalkEntity) {
        BicycleWalkGateway.delete(entity.id)
    }

    override fun saveChanges(entity: BicycleWalkEntity) {
        entity.run {
            BicycleWalkGateway.update(DataBicycleWalkEntity(
                    id = id,
                    title = title,
                    description = description,
                    walkType = WalkTypeMapper.fromWalkType(walkType),
                    duration = duration,
                    distance = distance,
                    leaderStatus = LeaderStatusMapper.fromLeaderStatus(leaderStatus),
                    organizer_id = organizer.id,
                    leader_id = leader?.id,
                    price = price,
                    paymentType = PaymentTypeMapper.fromPaymentType(paymentType),
                    date = date
            ))
        }
    }
}