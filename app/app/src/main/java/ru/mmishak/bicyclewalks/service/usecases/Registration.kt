package ru.mmishak.bicyclewalks.service.usecases

import ru.mmishak.bicyclewalks.domain.repositories.implementation.LeaderRepository
import ru.mmishak.bicyclewalks.domain.repositories.implementation.OrganizerRepository
import ru.mmishak.bicyclewalks.service.util.Utils

object Registration {
    fun registerCyclist(login: String, password: String, email: String, firstName: String, lastName: String, phone: String): Boolean {
        TODO()
    }

    fun registerLeader(login: String, password: String, email: String, firstName: String, lastName: String, phone: String): Boolean {
        LeaderRepository().create(login, Utils.encodePassword(password), email, firstName, lastName, phone)
        return true
    }

    fun registerOrganizer(login: String, password: String, email: String, title: String): Boolean {
        OrganizerRepository().create(login, Utils.encodePassword(password), email, title)
        return true
    }
}