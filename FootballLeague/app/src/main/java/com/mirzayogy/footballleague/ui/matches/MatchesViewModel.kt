package com.mirzayogy.footballleague.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.model.LeagueResponse

class MatchesViewModel : ViewModel() {
    private val _leagueResponse = MutableLiveData<LeagueResponse>()
//    var league: LiveData<LeagueResponse> = Transformations.map(_leagueResponse) {
//
//    }
}