package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.review.base.ReviewEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity

object DataBaseImitator {
    val bicycleWalks = mutableListOf<BicycleWalkEntity>()
    val organizers = mutableListOf<OrganizerEntity>()
    val leaders = mutableListOf<LeaderEntity>()
    val cyclists = mutableListOf<CyclistEntity>()
    val reviews = mutableListOf<ReviewEntity>()

    fun reset() {
        bicycleWalks.clear()
        organizers.clear()
        leaders.clear()
        cyclists.clear()
        reviews.clear()
    }

    fun loginExists(login: String) = organizers.loginExists(login) || leaders.loginExists(login) || cyclists.loginExists(login)
}