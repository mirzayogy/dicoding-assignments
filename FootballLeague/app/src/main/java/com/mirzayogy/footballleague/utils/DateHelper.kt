package com.mirzayogy.footballleague.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun customDate(date: String): String {
        val sdf1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val sdf2 = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        return try {
            val dateDate: Date = sdf1.parse(date)
            sdf2.format(dateDate)
        }catch (e: ParseException){
            e.toString()
        }
    }
}