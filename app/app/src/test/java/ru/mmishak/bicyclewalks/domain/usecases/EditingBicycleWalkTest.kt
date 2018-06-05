package ru.mmishak.bicyclewalks.domain.usecases

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.users.BaseLeader
import ru.mmishak.bicyclewalks.domain.entities.users.BaseOrganizer
import ru.mmishak.bicyclewalks.domain.entities.users.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.Organizer
import ru.mmishak.bicyclewalks.domain.repositories.base.BicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.repositories.base.LeaderRepository
import ru.mmishak.bicyclewalks.domain.repositories.base.OrganizerRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.DataBaseImitator
import ru.mmishak.bicyclewalks.domain.usecases.repositories.MockedBicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.MockedLeaderRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.MockedOrganizerRepository
import ru.mmishak.bicyclewalks.domain.util.DateTimeHelper

class EditingBicycleWalkTest {
    private lateinit var bicycleWalkRepository: BicycleWalkRepository
    private lateinit var organizerRepository: OrganizerRepository
    private lateinit var leaderRepository: LeaderRepository
    private lateinit var organizer: Organizer
    private lateinit var leader: Leader
    private lateinit var walk: BicycleWalk

    @Before
    fun setUp() {
        DataBaseImitator.reset()
        bicycleWalkRepository = MockedBicycleWalkRepository()
        organizerRepository = MockedOrganizerRepository()
        leaderRepository = MockedLeaderRepository()
        organizer = organizerRepository.create("login1", "password1995", "a@b.ru", "Bicycle Walk Company")
        leader = leaderRepository.create("login2", "pass", "ads@da.ru", "Anton", "Antonov", "+79000000000")
        walk = bicycleWalkRepository.create(
                title = "title",
                description = "some description",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(3),
                distance = 14,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                price = 200,
                paymentType = PaymentType.PAY,
                organizer = organizer,
                leader = leader,
                leaderStatus = LeaderStatus.ACCEPTED
        )
    }

    @Test
    fun editingBicycleWalk() {
        val organizerWalksList = bicycleWalkRepository.getAllForOrganizer(organizer)
        val organizerWalk = organizerWalksList[0]
        val newDescription = "changed description"

        organizerWalk.description = newDescription

        bicycleWalkRepository.saveChanges(organizerWalk)

        val newOrganizerWalksList = bicycleWalkRepository.getAllForOrganizer(organizer)
        Assert.assertEquals("Walk not changed.", newDescription, newOrganizerWalksList[0].description)
    }
}