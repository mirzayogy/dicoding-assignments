package com.mirzayogy.footballleague.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.model.LeagueResponse

class MatchesViewModel : ViewModel() {
    private lateinit var leagueId: String

    fun setSelectedLeague(leagueId:String){
        this.leagueId = leagueId
    }

//    fun getNextMatches():List
}