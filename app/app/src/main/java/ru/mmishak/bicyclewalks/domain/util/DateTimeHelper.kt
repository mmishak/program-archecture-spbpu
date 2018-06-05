package ru.mmishak.bicyclewalks.domain.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun hourToMillis(hour: Int): Long = hour.toLong() * 60 * 60 * 1000

    fun dateToMillis(date: String): Long {
        return dateFormat.parse(date).time
    }

    fun equalsDates(a: Long, b: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = a
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day =  calendar.get(Calendar.DAY_OF_MONTH)
        calendar.timeInMillis = b
        return year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) && day == calendar.get(Calendar.DAY_OF_MONTH)
    }
}