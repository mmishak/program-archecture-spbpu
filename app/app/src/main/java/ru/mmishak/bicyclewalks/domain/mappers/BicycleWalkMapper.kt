package ru.mmishak.bicyclewalks.domain.mappers

import ru.mmishak.bicyclewalks.data.entities.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.implementation.BicycleWalk
import ru.mmishak.bicyclewalks.domain.repositories.implementation.LeaderRepository
import ru.mmishak.bicyclewalks.domain.repositories.implementation.OrganizerRepository

object BicycleWalkMapper : DataMapper<BicycleWalkEntity, BicycleWalk> {
    override fun transform(entity: BicycleWalkEntity): BicycleWalk {
        return BicycleWalk(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                walkType = WalkTypeMapper.toWalkType(entity.walkType),
                duration = entity.duration,
                distance = 14,
                date = entity.date,
                price = 200,
                paymentType = PaymentTypeMapper.toPaymentType(entity.paymentType),
                organizer = OrganizerRepository().get(entity.organizer_id)!!,
                leader = entity.leader_id?.let { LeaderRepository().get(entity.leader_id) },
                cyclists = mutableListOf(),
                reviews = mutableListOf(),
                leaderStatus = LeaderStatusMapper.toLeaderStatus(entity.leaderStatus)
        )
    }

    override fun transform(collection: Collection<BicycleWalkEntity>): Collection<BicycleWalk> {
        return collection.map { transform(it) }
    }
}