package ru.mmishak.bicyclewalks.domain.repositories

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.users.Cyclist
import ru.mmishak.bicyclewalks.domain.entities.users.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.Organizer

interface BicycleWalkRepository<T : BicycleWalk> : BaseRepository<T> {
    fun getAllForOrganizer(organizer: Organizer): List<T>

    fun getAllForLeader(leader: Leader): List<T>

    fun getAllForCyclist(cyclist: Cyclist): List<T>

    fun getAllAccepted(): List<T>
}