package org.infinity.calendar.share.data

enum class CalendarType(val res: String) {
    BS(res = "BS"),
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

