package org.infinity.calendar.share.calendarModel

import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.data.CalendarDay
import org.infinity.calendar.share.data.CalendarMonth

interface CalendarModel {

    val today: CalendarDate

    fun numberOfDaysInMonth(): List<CalendarDay>

    fun getPage(year: Int, month: Int): Int

    fun getYearRange(): IntRange

    fun getMonth(calendarDate: CalendarDate): CalendarMonth

    fun getMonth(page: Int): CalendarMonth

    fun getNumberOfMonths(): Int

}