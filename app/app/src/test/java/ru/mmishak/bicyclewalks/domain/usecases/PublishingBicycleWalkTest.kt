package ru.mmishak.bicyclewalks.domain.usecases

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
import ru.mmishak.bicyclewalks.domain.repositories.base.BicycleWalkRepository
import ru.mmishak.bicyclewalks.domain.repositories.base.LeaderRepository
import ru.mmishak.bicyclewalks.domain.repositories.base.OrganizerRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.*
import ru.mmishak.bicyclewalks.domain.util.DateTimeHelper

class PublishingBicycleWalkTest {
    private lateinit var bicycleWalkRepository: BicycleWalkRepository
    private lateinit var organizerRepository: OrganizerRepository
    private lateinit var leaderRepository: LeaderRepository
    private lateinit var organizer: OrganizerEntity
    private lateinit var leader: LeaderEntity

    @Before
    fun setUp() {
        DataBaseImitator.reset()
        bicycleWalkRepository = MockedBicycleWalkRepository()
        organizerRepository = MockedOrganizerRepository()
        leaderRepository = MockedLeaderRepository()
        organizerRepository.create("login1", "password1995", "a@b.ru", "Bicycle Walk Company") {
            organizer = it!!
        }
        leaderRepository.create("login2", "pass", "ads@da.ru", "Anton", "Antonov", "+79000000000") {
            leader = it!!
        }
    }

    @Test
    fun publishingBicycleWalk() {
        bicycleWalkRepository.create(
                title = "title",
                description = "some description",
                walkType = WalkType.WALK,
                duration = DateTimeHelper.hourToMillis(3),
                distance = 14,
                date = DateTimeHelper.dateToMillis("12.06.2018"),
                price = 200,
                paymentType = PaymentType.PAY,
                organizer = organizer,
                leader = leader
        ) { walk ->
            walk ?: throw Exception("Walk not created.")

            bicycleWalkRepository.get(walk.id) {
                Assert.assertNotNull("Bicycle walk not added to database.", it)
                Assert.assertEquals("Bicycle walk from database not equals created.", walk, it)
            }

            bicycleWalkRepository.getAllForLeader(leader) { isSuccess, leaderWalks ->
                if (!isSuccess) throw Exception("Get leader walks fail.")

                val requestedLeaderWalks = leaderWalks.filter { it.leaderStatus == LeaderStatus.WAITING_ACCEPT }

                Assert.assertTrue("Leader not receive request.", requestedLeaderWalks.contains(walk))

                leader.acceptWalkRequest(walk)

                Assert.assertEquals("Walk not accepted.", LeaderStatus.ACCEPTED, walk.leaderStatus)
            }
        }
    }
}