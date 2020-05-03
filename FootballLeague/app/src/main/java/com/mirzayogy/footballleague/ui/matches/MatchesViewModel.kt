package com.mirzayogy.footballleague.ui.matches

import androidx.lifecycle.ViewModel

class MatchesViewModel : ViewModel() {
    private lateinit var leagueId: String

    fun setSelectedLeague(leagueId:String){
        this.leagueId = leagueId
    }

//    fun getNextMatches():List
}