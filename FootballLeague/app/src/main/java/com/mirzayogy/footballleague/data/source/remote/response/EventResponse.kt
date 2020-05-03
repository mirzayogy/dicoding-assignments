package com.mirzayogy.footballleague.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventResponse(
    var idEvent: String,
    var strHomeTeam: String,
    var idHomeTeam: String,
    var intHomeScore: String?,
    var strAwayTeam: String,
    var idAwayTeam: String,
    var intAwayScore: String?,
    var intSpectators:String?,
    var dateEvent: String,
    var strTime: String
) : Parcelable