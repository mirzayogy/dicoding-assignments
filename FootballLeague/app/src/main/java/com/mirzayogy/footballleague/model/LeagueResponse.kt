package com.mirzayogy.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueResponse(
    var id: String,
    var name: String,
    var badge: String,
    var description: String

) :Parcelable