package org.infinity.calendar.share.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
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

@OptIn(ExperimentalTime::class)
fun CalendarDate.toEpochMillis(): Long {
    val ad = DateConverter.bsToAd(year, month, dayOfMonth)
    val adDate = LocalDate(ad.year, ad.month, ad.day)

    return LocalDateTime(
        year = adDate.year,
        month = adDate.month,
        day = adDate.day,
        hour = 0,
        minute = 0
    ).toInstant(TimeZone.UTC).toEpochMilliseconds()
}

@OptIn(ExperimentalTime::class)
fun LocalDate.Companion.now(): LocalDate =
    Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date
enum class CalendarType(val res: String) {
    BS(res = "AD"),
    AD(res = "AD");

    companion object {
        private val typeMap =
            enumValues<CalendarType>().associateBy { it.res.lowercase() }

        fun get(typeName: String): CalendarType =
            CalendarType.typeMap[typeName.trim().lowercase()] ?: BS

        val list: List<CalendarType>
            get() = entries.toList().map { it }

    }
}

fun CalendarType.isBS(): Boolean {
    return this == CalendarType.BS
}