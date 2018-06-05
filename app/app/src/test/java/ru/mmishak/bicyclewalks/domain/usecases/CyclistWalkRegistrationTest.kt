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
        organizerRepository.create("login1", "password1995", "a@b.ru", "Bicycle Walk Company") {
            organizer = it!!
        }
        cyclistRepository.create("login2", "pass", "ads@da.ru", "Anton", "Antonov", "+79000000000") {
            cyclist = it!!
        }
        bicycleWalkRepository.create(
                title = "title1",
                description = "some description 1",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(4),
                distance = 14,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                paymentType = PaymentType.FREE,
                organizer = organizer,
                leaderStatus = LeaderStatus.WITHOUT_LEADER
        ) { walk1 = it!! }
        bicycleWalkRepository.create(
                title = "title2",
                description = "some description 2",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(3),
                distance = 17,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                paymentType = PaymentType.FREE,
                organizer = organizer,
                leaderStatus = LeaderStatus.WITHOUT_LEADER
        ) { walk2 = it!! }
        bicycleWalkRepository.create(
                title = "title3",
                description = "some description 3",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(5),
                distance = 13,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                paymentType = PaymentType.FREE,
                organizer = organizer,
                leaderStatus = LeaderStatus.WITHOUT_LEADER
        ) { walk3 = it!! }
    }

    @Test
    fun cyclistWalkRegistration() {
        bicycleWalkRepository.search(paymentType = PaymentType.FREE, maxDistance = 14) { isSuccess, searchResult ->
            if (!isSuccess) throw Exception("Search fail.")

            Assert.assertTrue("Bad search response.", searchResult.contains(walk1))
            Assert.assertFalse("Bad search response.", searchResult.contains(walk2))
            Assert.assertTrue("Bad search response.", searchResult.contains(walk3))

            val walk = searchResult[0]

            cyclist.registerToWalk(walk, callback = { isRegisterSuccess, _ ->
                if (isRegisterSuccess) {
                    bicycleWalkRepository.getAllForCyclist(cyclist) { _, cyclistWalks ->
                        Assert.assertFalse("CyclistEntity not found after registration to walk.", cyclistWalks.isEmpty())
                        Assert.assertTrue("CyclistEntity not found after registration to walk.", cyclistWalks.contains(walk))
                    }
                } else {
                    throw Exception("CyclistEntity registration failed.")
                }
            })
        }
    }
}