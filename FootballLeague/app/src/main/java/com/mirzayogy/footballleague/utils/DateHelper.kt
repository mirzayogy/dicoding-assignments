package com.mirzayogy.footballleague.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun customDate(date: Date): String {
        return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(date)
    }
}