package ru.mmishak.bicyclewalks.domain.usecases

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.repositories.base.BicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.repositories.base.CyclistRepository
import ru.mmishak.bicyclewalks.domain.repositories.base.OrganizerRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.DataBaseImitator
import ru.mmishak.bicyclewalks.domain.usecases.repositories.MockedBicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.MockedCyclistRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.MockedOrganizerRepository
import ru.mmishak.bicyclewalks.domain.util.DateTimeHelper

class CyclistWalkRegistrationTest {
    private lateinit var bicycleWalkRepository: BicycleWalkRepository
    private lateinit var cyclistRepository: CyclistRepository
    private lateinit var organizerRepository: OrganizerRepository
    private lateinit var organizer: OrganizerEntity
    private lateinit var cyclist: CyclistEntity
    private lateinit var walk1: BicycleWalkEntity
    private lateinit var walk2: BicycleWalkEntity
    private lateinit var walk3: BicycleWalkEntity

    @Before
    fun setUp() {
        DataBaseImitator.reset()
        bicycleWalkRepository = MockedBicycleWalkRepository()
        cyclistRepository = MockedCyclistRepository()
        organizerRepository = MockedOrganizerRepository()
        organizer = organizerRepository.create("login1", "password1995", "a@b.ru", "Bicycle Walk Company")
        cyclist = cyclistRepository.create("login2", "pass", "ads@da.ru", "Anton", "Antonov", "+79000000000")
        walk1 = bicycleWalkRepository.create(
                title = "title1",
                description = "some description 1",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(4),
                distance = 14,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                paymentType = PaymentType.FREE,
                organizer = organizer,
                leaderStatus = LeaderStatus.WITHOUT_LEADER
        )
        walk2 = bicycleWalkRepository.create(
                title = "title2",
                description = "some description 2",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(3),
                distance = 17,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                paymentType = PaymentType.FREE,
                organizer = organizer,
                leaderStatus = LeaderStatus.WITHOUT_LEADER
        )
        walk3 = bicycleWalkRepository.create(
                title = "title3",
                description = "some description 3",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(5),
                distance = 13,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                paymentType = PaymentType.FREE,
                organizer = organizer,
                leaderStatus = LeaderStatus.WITHOUT_LEADER
        )
    }

    @Test
    fun cyclistWalkRegistration() {
        val searchWalksResult = bicycleWalkRepository.search(paymentType = PaymentType.FREE, maxDistance = 14)
        Assert.assertTrue("Bad search response.", searchWalksResult.contains(walk1))
        Assert.assertFalse("Bad search response.", searchWalksResult.contains(walk2))
        Assert.assertTrue("Bad search response.", searchWalksResult.contains(walk3))

        val walk = searchWalksResult[0]

        cyclist.registerToWalk(walk, callback = {isSuccess, _ ->
            if (isSuccess) {
                val cyclistWalks = bicycleWalkRepository.getAllForCyclist(cyclist)
                Assert.assertFalse("CyclistEntity not found after registration to walk.", cyclistWalks.isEmpty())
                Assert.assertTrue("CyclistEntity not found after registration to walk.", cyclistWalks.contains(walk))
            } else {
                throw Exception("CyclistEntity registration failed.")
            }
        })
    }
}