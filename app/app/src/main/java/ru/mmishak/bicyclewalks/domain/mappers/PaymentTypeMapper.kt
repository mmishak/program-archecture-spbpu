package ru.mmishak.bicyclewalks.domain.mappers

import ru.mmishak.bicyclewalks.data.database.tables.BicycleWalkTable
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.PaymentType
import ru.mmishak.bicyclewalks.domain.entities.bicyclewalk.enums.WalkType

object PaymentTypeMapper {
    fun toPaymentType(type: Int): PaymentType {
        return when (type) {
            BicycleWalkTable.TYPE_PAY -> PaymentType.PAY
            BicycleWalkTable.TYPE_FREE -> PaymentType.FREE
            else -> PaymentType.FREE
        }
    }

    fun fromPaymentType(paymentType: PaymentType): Int {
        return when (paymentType) {
            PaymentType.FREE -> BicycleWalkTable.TYPE_FREE
            PaymentType.PAY -> BicycleWalkTable.TYPE_PAY
        }
    }
}