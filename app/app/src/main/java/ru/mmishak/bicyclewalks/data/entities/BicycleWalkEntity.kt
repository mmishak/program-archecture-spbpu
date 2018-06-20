package ru.mmishak.bicyclewalks.data.entities

data class BicycleWalkEntity (
        override val id: Int,
        val title: String,
        val description: String,
        val walkType: Int,
        val duration: Long,
        val distance: Int,
        val date: Long,
        val price: Int,
        val paymentType: Int,
        val organizer_id: Int,
        val leader_id: Int?,
        val leaderStatus: Int
) : Entity