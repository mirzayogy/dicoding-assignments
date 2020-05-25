package com.mirzayogy.footballleague.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventResponse(
    var idEvent: Long,
    var strHomeTeam: String,
    var idHomeTeam: String,
    var intHomeScore: String?,
    var strHomeGoalDetails: String?,
    var strAwayTeam: String,
    var idAwayTeam: String,
    var intAwayScore: String?,
    var strAwayGoalDetails: String?,
    var intSpectators:String?,
    var dateEvent: String?,
    var strTime: String,
    var strEvent: String,
    var strLeague: String
) : Parcelable{
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID_EVENT: String = "ID_EVENT"
        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        const val STR_HOME_GOAL_DETAILS: String = "STR_HOME_GOAL_DETAILS"
        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        const val STR_AWAY_GOAL_DETAILS: String = "STR_AWAY_GOAL_DETAILS"
        const val INT_SPECTATORS: String = "INT_SPECTATORS"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val STR_TIME: String = "STR_TIME"
        const val STR_EVENT: String = "STR_EVENT"
        const val STR_LEAGUE: String = "STR_LEAGUE"
    }
}