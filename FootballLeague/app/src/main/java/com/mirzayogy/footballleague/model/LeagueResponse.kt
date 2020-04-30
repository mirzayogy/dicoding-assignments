package com.mirzayogy.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueResponse(
    var id: String?,
    var name: String?,
    var description: String?,
    var badge: String?

) :Parcelable