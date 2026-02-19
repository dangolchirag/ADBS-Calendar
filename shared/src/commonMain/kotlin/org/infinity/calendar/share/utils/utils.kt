package org.infinity.calendar.share.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.infinity.calendar.share.calendarModel.CalendarModel
import org.infinity.calendar.share.data.CalendarDate
import org.infinity.calendar.share.data.CalendarType
import org.infinity.lib.DateConverter
import org.jetbrains.compose.resources.stringArrayResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


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

fun CalendarType.isBS(): Boolean {
    return this == CalendarType.BS
}


fun LocalDate.daysInMonth(): Int {
    val firstDayOfMonth = LocalDate(year, month, 1)
    val firstDayOfNextMonth = firstDayOfMonth.plus(DatePeriod(months = 1))
    return firstDayOfMonth.daysUntil(firstDayOfNextMonth)
}
@Composable
fun CalendarModel.todayFormattedBSDate(): String {
//    val months = stringArrayResource(SharedRes.Arrays.months)
//    val weeks = stringArrayResource(SharedRes.Arrays.weeksDays)
//    val monthsCalendar = numberOfDaysInMonth().find { it.day == today.dayOfMonth }
//    return "${today.dayOfMonth.mapNumbers} ${months[today.month - 1]}, ${today.year.mapNumbers} ${
//        weeks[monthsCalendar?.let {
//            it.dayOfWeek - 1
//        } ?: 0]
//    }"
    return "2082-09-08"
}

@OptIn(ExperimentalTime::class)
fun CalendarModel.todayFormattedADDate(): String {
    return try {

        val instant = Clock.System.now()
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val dayOfWeekName = localDateTime.date.dayOfWeek.name.lowercase()
            .replaceFirstChar { it.uppercase() }
        localDateTime.format(
            LocalDateTime.Format {
                day(padding = Padding.ZERO) // Day (no padding)
                chars(" ")
                monthName(MonthNames.ENGLISH_FULL) // Full month name (e.g., "June")
                chars(", ")
                year() // Year
                chars(" ")
                chars(dayOfWeekName)
            }
        )
    } catch (e: Exception) {
        e.printStackTrace()
        "Invalid date: $this" // Fallback for parsing errors
    }
}

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
    )
