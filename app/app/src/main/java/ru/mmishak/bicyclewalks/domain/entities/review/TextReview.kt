package ru.mmishak.bicyclewalks.domain.entities.review

import ru.mmishak.bicyclewalks.domain.entities.users.Human

class TextReview(
        id: Int,
        author: Human,
        val text: String
) : BaseReview(id, author)