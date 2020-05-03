package com.mirzayogy.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventResponse(
    var idEvent: String,
    var strHomeTeam: String,
    var idHomeTeam: String,
    var strAwayTeam: String,
    var idrAwayTeam: String,
    var dateEvent: String,
    var strTime: String
) : Parcelable