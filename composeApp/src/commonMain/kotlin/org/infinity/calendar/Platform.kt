package org.infinity.calendar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform