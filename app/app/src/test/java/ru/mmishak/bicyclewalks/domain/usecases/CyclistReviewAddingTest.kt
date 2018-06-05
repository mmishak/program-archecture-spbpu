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
import ru.mmishak.bicyclewalks.domain.repositories.base.ReviewRepository
import ru.mmishak.bicyclewalks.domain.usecases.repositories.*
import ru.mmishak.bicyclewalks.domain.util.DateTimeHelper

class CyclistReviewAddingTest {
    private lateinit var bicycleWalkRepository: BicycleWalkRepository
    private lateinit var cyclistRepository: CyclistRepository
    private lateinit var reviewRepository: ReviewRepository
    private lateinit var organizerRepository: OrganizerRepository
    private lateinit var organizer: OrganizerEntity
    private lateinit var cyclist: CyclistEntity
    private lateinit var walk: BicycleWalkEntity

    @Before
    fun setUp() {
        DataBaseImitator.reset()
        cyclistRepository = MockedCyclistRepository()
        reviewRepository = MockedReviewRepository()
        organizerRepository = MockedOrganizerRepository()
        bicycleWalkRepository = MockedBicycleWalkRepository()
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
                leaderStatus = LeaderStatus.WITHOUT_LEADER,
                cyclists = mutableListOf(cyclist)
        ) { walk = it!! }
    }

    @Test
    fun cyclistReviewAdding() {
        bicycleWalkRepository.getAllForCyclist(cyclist) { isSuccess, cyclistWalks ->
            if (!isSuccess) throw Exception("Get cyclist walks fail.")
            if (cyclistWalks.isEmpty()) throw Exception("Get cyclist walks fail.")

            val cyclistWalk = cyclistWalks[0]

            reviewRepository.createTextReview(
                    author = cyclist,
                    bicycleWalk = cyclistWalk,
                    time = System.currentTimeMillis(),
                    text = "Good walk!"
            ) {
                Assert.assertNotNull("Review add fail.", it)
            }
        }
    }
}