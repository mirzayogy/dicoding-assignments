package com.mirzayogy.footballleague.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class EventResponse(
    var idEvent: String,
    var strHomeTeam: String,
    var idHomeTeam: String,
    var intHomeScore: String?,
    var strHomeGoalDetails: String?,
    var strAwayTeam: String,
    var idAwayTeam: String,
    var intAwayScore: String?,
    var strAwayGoalDetails: String?,
    var intSpectators:String?,
    var dateEvent: Date?,
    var strTime: String,
    var strEvent: String,
    var strLeague: String
) : Parcelable