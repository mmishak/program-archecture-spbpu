package ru.mmishak.bicyclewalks.domain.usecases.repositories

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.users.*

object DataBaseImitator {
    val bicycleWalks = mutableListOf<BicycleWalk>()
    val organizers = mutableListOf<Organizer>()
    val leaders = mutableListOf<Leader>()
    val cyclists = mutableListOf<Cyclist>()

    fun reset() {
        bicycleWalks.clear()
        organizers.clear()
        leaders.clear()
        cyclists.clear()
    }

    fun loginExists(login: String) = organizers.loginExists(login) || leaders.loginExists(login) || cyclists.loginExists(login)
}