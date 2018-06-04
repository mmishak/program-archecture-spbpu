package ru.mmishak.bicyclewalks.domain.entities.bicyclewalk

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType
import ru.mmishak.bicyclewalks.domain.entities.review.Review
import ru.mmishak.bicyclewalks.domain.entities.users.Cyclist
import ru.mmishak.bicyclewalks.domain.entities.users.Leader
import ru.mmishak.bicyclewalks.domain.entities.users.Organizer

class PayBicycleWalk(
        id: Int,
        title: String,
        description: String,
        type: WalkType,
        duration: Long,
        distance: Int,
        date: Long,
        price: Int = 0,
        organizer: Organizer,
        cyclists: List<Cyclist>,
        reviews: List<Review>,
        leader: Leader? = null
) : BaseBicycleWalk(id, title, description, type, duration, distance, date, price, PaymentType.PAY, organizer, cyclists, reviews, leader)