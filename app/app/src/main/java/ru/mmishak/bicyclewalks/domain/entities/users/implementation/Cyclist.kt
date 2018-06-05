package ru.mmishak.bicyclewalks.domain.entities.users.implementation

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.base.BicycleWalkEntity
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.users.base.CyclistEntity

data class Cyclist(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val firstName: String,
        override val secondName: String,
        override val phone: String
) : CyclistEntity {
    override fun registerToWalk(walk: BicycleWalkEntity, callback: ((isSuccess: Boolean, walk: BicycleWalkEntity) -> Unit)?) {
        if (!walk.isPublished()) callback?.invoke(false, walk)

        if (walk.paymentType == PaymentType.PAY && walk.price > 0) {
            // TODO: request payment
            Thread.sleep(2000)
            // ...

            walk.cyclists.add(this)
            callback?.invoke(true, walk)
        } else {
            walk.cyclists.add(this)
            callback?.invoke(true, walk)
        }
    }

    override fun addReview(walk: BicycleWalkEntity) {
        TODO("not implemented")
    }
}