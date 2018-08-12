package fr.pasithee.moodlog.util;

import java.util.*


fun getPastWeekTimestamps() : Pair<Double, Double> {
    val cal = getCalendarForNow()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    val maxDate = cal.timeInMillis.toDouble()
    cal.add(Calendar.DAY_OF_YEAR, -7)
    val minDate = cal.timeInMillis.toDouble()
    return Pair(minDate, maxDate)
}

fun getPastWeekDates() : Pair<Date, Date> {
    val cal = getCalendarForNow()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    val maxDate = cal.time
    cal.add(Calendar.DAY_OF_YEAR, -7)
    val minDate = cal.time
    return Pair(minDate, maxDate)
}

fun getCalendarForNow(): Calendar {
    return getCalendarForDate(-1, -1, -1)
}

fun getCalendarForDate(year: Int, month: Int, day: Int): Calendar {
    val cal = Calendar.getInstance()
    if (year >= 0 && month >= 0 && day >= 0) {
        cal.set(year, month, day)
    }
    cal.set(Calendar.HOUR, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    cal.set(Calendar.AM_PM, Calendar.AM)
    return cal
}
