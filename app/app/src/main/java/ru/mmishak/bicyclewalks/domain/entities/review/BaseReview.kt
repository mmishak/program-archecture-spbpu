package ru.mmishak.bicyclewalks.domain.entities.review

import ru.mmishak.bicyclewalks.domain.entities.Entity
import ru.mmishak.bicyclewalks.domain.entities.users.Human

abstract class BaseReview(
        override val id: Int,
        override val author: Human
) : Review