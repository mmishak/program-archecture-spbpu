package ru.mmishak.bicyclewalks.service.usecases

import ru.mmishak.bicyclewalks.Config
import ru.mmishak.bicyclewalks.domain.repositories.implementation.LeaderRepository
import ru.mmishak.bicyclewalks.domain.repositories.implementation.OrganizerRepository
import ru.mmishak.bicyclewalks.service.util.Utils

object Authentication {
    fun authLeader(login: String, password: String): Boolean {
        val leader = LeaderRepository().getAll().find { it.login == login && it.password == Utils.encodePassword(password) }
        leader ?: return false
        Config.currentUser = leader
        return true
    }

    fun authOrganizer(login: String, password: String): Boolean {
        val organizer = OrganizerRepository().getAll().find { it.login == login && it.password == Utils.encodePassword(password) }
        organizer ?: return false
        Config.currentUser = organizer
        return true
    }

    fun logout() {
        Config.currentUser = null
    }
}