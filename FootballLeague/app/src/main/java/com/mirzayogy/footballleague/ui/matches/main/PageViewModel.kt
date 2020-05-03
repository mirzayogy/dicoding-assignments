package com.mirzayogy.footballleague.ui.matches.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.data.source.remote.response.LeagueResponse

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    var league: MutableLiveData<LeagueResponse>? = null
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}