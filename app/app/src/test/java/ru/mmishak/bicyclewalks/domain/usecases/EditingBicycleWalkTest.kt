package ru.mmishak.bicyclewalks.domain.usecases

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.LeaderStatus
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.users.base.LeaderEntity
import ru.mmishak.bicyclewalks.domain.entities.users.base.OrganizerEntity
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
    private lateinit var organizer: OrganizerEntity
    private lateinit var leader: LeaderEntity
    private lateinit var walk: BicycleWalkEntity

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
                leader = leader,
                leaderStatus = LeaderStatus.ACCEPTED
        ) { walk = it!! }
    }

    @Test
    fun editingBicycleWalk() {
        bicycleWalkRepository.getAllForOrganizer(organizer) { isSuccess, organizerWalks ->
            if (!isSuccess) throw Exception("Get organizer walks fail.")

            val organizerWalk = organizerWalks[0]
            val newDescription = "changed description"

            organizerWalk.description = newDescription

            bicycleWalkRepository.saveChanges(organizerWalk) { isSaveChangesSuccess ->
                if (!isSaveChangesSuccess) throw Exception("Save changes fail.")

                bicycleWalkRepository.getAllForOrganizer(organizer) { isSuccess, newOrganizerWalks ->
                    if (!isSuccess) throw Exception("Get new organizer walks fail.")
                    Assert.assertEquals("Walk not changed.", newDescription, newOrganizerWalks[0].description)
                }
            }
        }

    }
}