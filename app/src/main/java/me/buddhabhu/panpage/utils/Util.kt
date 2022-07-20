package me.buddhabhu.panpage.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val MAX_VALID_YR = 9999
const val MIN_VALID_YR = 1800

fun isValidPAN(number: String): Boolean {
    if(number.length != 10)
        return false
    val pattern = Regex("[A-Z]{5}[0-9]{4}[A-Z]{1}")
    if(number.matches(pattern))
        return true
    return false
}

@RequiresApi(Build.VERSION_CODES.O)
fun isValidBirthDate(day: String, month: String, year: String): Boolean {
    val newDay = if(day.length == 2) day else "0$day"
    val newMonth = if(month.length == 2) month else "0$month"

    if(!isValidDate(newDay.toInt(), newMonth.toInt(), year.toInt())) {
        return false
    }

    val inputDate = "$year-$newMonth-$newDay"

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val currentDate = LocalDateTime.now().format(formatter)

    val sdf = SimpleDateFormat("yyyy-MM-dd")
    try {
        val firstDate: Date = sdf.parse(inputDate)
        val secondDate: Date = sdf.parse(currentDate)
        if(firstDate > secondDate)
            return false
    } catch(e: Exception) {
        return false
    }
    return true
}

fun isLeap(year: Int): Boolean {
    return (((year % 4 == 0) &&
            (year % 100 != 0)) ||
            (year % 400 == 0))
}

fun isValidDate(d: Int, m: Int, y: Int): Boolean {
    if (y > MAX_VALID_YR ||
        y < MIN_VALID_YR)
        return false
    if (m < 1 || m > 12)
        return false
    if (d < 1 || d > 31)
        return false

    // Handle February month
    // with leap year
    if (m == 2)
    {
        if (isLeap(y))
            return (d <= 29)
        else
            return (d <= 28)
    }

    // Months of April, June,
    // Sept and Nov must have
    // number of days less than
    // or equal to 30.
    if (m == 4 || m == 6 ||
        m == 9 || m == 11)
        return (d <= 30)

    return true;
}
