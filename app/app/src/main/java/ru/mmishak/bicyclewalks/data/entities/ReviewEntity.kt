package ru.mmishak.bicyclewalks.data.entities

data class ReviewEntity(
        override val id: Int,
        val author: HumanEntity,
        val bicycleWalk: BicycleWalkEntity,
        val time: Long,
        val text: String
) : Entity