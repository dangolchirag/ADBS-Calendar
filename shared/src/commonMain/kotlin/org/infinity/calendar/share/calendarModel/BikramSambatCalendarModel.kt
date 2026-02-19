package org.infinity.calendar.share.calendarModel

import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.number
import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.data.CalendarDay
import org.infinity.calendar.share.data.CalendarMonth
import org.infinity.calendar.share.utils.daysInMonth
import org.infinity.calendar.share.utils.now
import org.infinity.lib.BSPointer
import org.infinity.lib.DateConverter
import org.infinity.lib.Year

class BikramSambatCalendarModel() : CalendarModel {
    override val today: CalendarDate
        get() {
            val today = LocalDate.now()
            val nepaliDate = DateConverter.adToBs(
                year = today.year,
                month = today.month.number,
                day = today.day
            )
            return CalendarDate(
                year = nepaliDate.year,
                month = nepaliDate.month,
                dayOfMonth = nepaliDate.day,
                page = getPage(
                    nepaliDate.year, month = nepaliDate.month
                )
            )
        }

    override fun numberOfDaysInMonth(): List<CalendarDay> {
        val range = 1..BSPointer.getNumOfDaysInMonth(
            year = Year.ofValue(today.year),
            month = today.month
        )

        val firstDayAD = DateConverter.bsToAd(today.year, today.month, 1)
        val localDate = LocalDate(firstDayAD.year, firstDayAD.month, firstDayAD.day)
        var dayOfWeek = getDayNumberSundayFirst(localDate)
        return range.toList().map { day ->
            if (dayOfWeek == 8) {
                dayOfWeek = 1
            }
            val dayCalendar = CalendarDay(dayOfWeek, day, dayOfWeek == 7)
            dayOfWeek++
            dayCalendar
        }
    }

    private fun getDayNumberSundayFirst(date: LocalDate = LocalDate.now()): Int {
        val day = date.dayOfWeek.isoDayNumber
        return if (day == 7) 1 else day + 1
    }

    override fun getPage(year: Int, month: Int): Int {
        return BSPointer.getPageIndex(
            year = Year.ofValue(year),
            month = month
        )
    }

    override fun getYearRange(): IntRange {
        return BSPointer.getYearRange()
    }

    override fun getMonth(calendarDate: CalendarDate): CalendarMonth {
        return getMonth(calendarDate.page)
    }

    override fun getMonth(page: Int): CalendarMonth {
        val (yearIndex, month) = BSPointer.getYearAndMonth(page)
        val year = BSPointer.getYear(yearIndex)
        val englishDate = DateConverter.bsToAd(
            year = year, month = month, day = 1
        )

        val local = LocalDate(
            englishDate.year,
            englishDate.month,
            englishDate.day
        )
        val numberOfDays = local.daysInMonth()
        val week = local.dayOfWeek
        return CalendarMonth(
            year = year,
            month = month,
            numberOfDays = BSPointer.getNumOfDaysInMonth(
                Year.ofIndex(yearIndex),
                month
            ),
            daysFromStartOfWeekToFirstOfMonth = week.isoDayNumber % 7,
            pageNum = page,
            startDate = englishDate.day,
            endDate = numberOfDays,
        )
    }

    override fun getNumberOfMonths(): Int {
        return BSPointer.getNumberOfAvailableMonths()
    }
}