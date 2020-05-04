package com.mirzayogy.footballleague.ui.matches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.data.source.local.entity.EventEntityResponse
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import id.co.teknobara.sirintik.data.source.remote.RetrofitRepository
import id.co.teknobara.sirintik.data.source.remote.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchesViewModel : ViewModel() {
    private lateinit var leagueId: String
    private val listNextEvents = MutableLiveData<ArrayList<EventResponse>>()
    private val listLastEvents = MutableLiveData<ArrayList<EventResponse>>()
    lateinit var retrofitServices : RetrofitServices

    fun setSelectedLeague(leagueId:String){
        this.leagueId = leagueId
    }

    fun setNextEvent(){
        val list= ArrayList<EventResponse>()
        this.retrofitServices = RetrofitRepository.create()
        this.retrofitServices.getNextEvent(this.leagueId).enqueue(object : Callback<EventEntityResponse>{
            override fun onFailure(call: Call<EventEntityResponse>, t: Throwable) {
                Log.e("RETROFIT onFailure", "errornya ${t.message}")
            }

            override fun onResponse(call: Call<EventEntityResponse>, response: Response<EventEntityResponse>) {
                if(response.isSuccessful){
                    val data = response.body()?.getResults()

                    data?.map{
                        val eventResponse = EventResponse(
                            it.idEvent,
                            it.strHomeTeam,
                            it.idHomeTeam,
                            it.intHomeScore,
                            it.strAwayTeam,
                            it.idAwayTeam,
                            it.intAwayScore,
                            it.intSpectators,
                            it.dateEvent,
                            it.strTime
                        )
                        list.add(eventResponse)
                    }
                    listNextEvents.postValue(list)
                }
            }
        })
    }

    fun setLastEvent(){
        val list= ArrayList<EventResponse>()
        this.retrofitServices = RetrofitRepository.create()
        this.retrofitServices.getLastEvent(this.leagueId).enqueue(object : Callback<EventEntityResponse>{
            override fun onFailure(call: Call<EventEntityResponse>, t: Throwable) {
                Log.e("RETROFIT onFailure", "errornya ${t.message}")
            }

            override fun onResponse(call: Call<EventEntityResponse>, response: Response<EventEntityResponse>) {
                if(response.isSuccessful){
                    val data = response.body()?.getResults()

                    data?.map{
                        val eventResponse = EventResponse(
                            it.idEvent,
                            it.strHomeTeam,
                            it.idHomeTeam,
                            it.intHomeScore,
                            it.strAwayTeam,
                            it.idAwayTeam,
                            it.intAwayScore,
                            it.intSpectators,
                            it.dateEvent,
                            it.strTime
                        )
                        list.add(eventResponse)
                    }
                    listLastEvents.postValue(list)
                }
            }
        })
    }

    fun getNextEvent(): LiveData<ArrayList<EventResponse>> = listNextEvents
    fun getLastEvent(): LiveData<ArrayList<EventResponse>> = listLastEvents
}