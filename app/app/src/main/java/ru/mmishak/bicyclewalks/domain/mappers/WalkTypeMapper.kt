package ru.mmishak.bicyclewalks.domain.mappers

import ru.mmishak.bicyclewalks.data.database.tables.BicycleWalkTable
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType

object WalkTypeMapper {
    fun toWalkType(type: Int): WalkType {
        return when (type) {
            BicycleWalkTable.TYPE_WALK -> WalkType.WALK
            BicycleWalkTable.TYPE_EXCURSION -> WalkType.EXCURSION
            else -> WalkType.WALK
        }
    }

    fun fromWalkType(walkType: WalkType): Int {
        return when (walkType) {
            WalkType.WALK -> BicycleWalkTable.TYPE_WALK
            WalkType.EXCURSION -> BicycleWalkTable.TYPE_EXCURSION
        }
    }
}