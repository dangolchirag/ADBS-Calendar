package org.infinity.calendar.share.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.infinity.calendar.share.utils.now
import org.infinity.lib.BSPointer
import org.infinity.lib.DateConverter
import org.infinity.lib.Year
import org.jetbrains.compose.resources.StringResource
import kotlin.time.Clock

import kotlin.time.ExperimentalTime


data class CalendarDate(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val page: Int
) : Comparable<CalendarDate> {
    override operator fun compareTo(other: CalendarDate): Int {
        return if (this.year == other.year) {
            if (this.month == other.month) {
                this.dayOfMonth.compareTo(other.dayOfMonth)
            } else {
                this.month.compareTo(other.month)
            }
        } else {
            this.year.compareTo(other.year)
        }
    }

    companion object {
        fun todayInBS(): CalendarDate {
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
                page = BSPointer.getPageIndex(
                    Year.ofValue(nepaliDate.year), month = nepaliDate.month
                )
            )
        }
    }
}

