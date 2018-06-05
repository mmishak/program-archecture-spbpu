package ru.mmishak.bicyclewalks.domain.entities.users

import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.BicycleWalk
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType

data class BaseCyclist(
        override val id: Int,
        override val login: String,
        override val password: String,
        override val email: String,
        override val firstName: String,
        override val secondName: String,
        override val phone: String
) : Cyclist {
    override fun registerToWalk(walk: BicycleWalk, callback: ((isSuccess: Boolean, walk: BicycleWalk) -> Unit)?) {
        if (!walk.isPublished()) callback?.invoke(false, walk)

        if (walk.paymentType == PaymentType.PAY && walk.price > 0) {
            //TODO: request payment
            walk.cyclists.add(this)
            callback?.invoke(true, walk)
        } else {
            walk.cyclists.add(this)
            callback?.invoke(true, walk)
        }
    }
}