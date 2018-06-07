package ru.mmishak.bicyclewalks.data.entities

import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity

data class BicycleWalkEntity (
        val id: Int,
        val title: String,
        val description: String,
        val walkType: Int,
        val duration: Long,
        val distance: Int,
        val date: Long,
        val price: Int,
        val paymentType: Int,
        val organizer: OrganizerEntity,
        val cyclists: MutableList<HumanEntity>,
        val reviews: MutableList<ReviewEntity>,
        val leader: LeaderEntity? = null,
        val leaderStatus: Int

)