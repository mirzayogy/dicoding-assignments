package com.mirzayogy.footballleague.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamResponse(
    var idTeam: String,
    var strTeam: String,
    var idLeague: String,
    var strStadium: String,
    var strStadiumLocation: String,
    var strTeamBadge: String

) : Parcelable