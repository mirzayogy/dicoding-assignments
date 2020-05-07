package com.mirzayogy.footballleague.ui.matches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.data.source.local.entity.EventEntityResponse
import com.mirzayogy.footballleague.data.source.local.entity.TeamEntityResponse
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import id.co.teknobara.sirintik.data.source.remote.RetrofitRepository
import com.mirzayogy.footballleague.data.source.remote.RetrofitServices
import com.mirzayogy.footballleague.data.source.remote.response.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.collections.ArrayList

class MatchesViewModel : ViewModel() {
    private lateinit var leagueId: String
    private val listNextEvents = MutableLiveData<ArrayList<EventResponse>>()
    private val listLastEvents = MutableLiveData<ArrayList<EventResponse>>()
    private val listHome = mutableListOf<String>()
    private val listAway = mutableListOf<String>()
    lateinit var retrofitServices : RetrofitServices

    fun setSelectedLeague(leagueId:String){
        this.leagueId = leagueId
    }

    fun setNextEvent(){

        this.retrofitServices = RetrofitRepository.createRX()
//        this.retrofitServices.getNextEventRX(this.leagueId)
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { data ->
//                    listNextEvents.postValue(data.getResults())
//                },
//                { error ->
//                    Log.e("Error", error.message)
//                }
//            )
        this.retrofitServices.getNextEventRX(this.leagueId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.from(it.getResults()) }
            .flatMap {itNext ->
                Observable.zip(
                    retrofitServices.getTeamDetailRX(itNext.idHomeTeam),
                    retrofitServices.getTeamDetailRX(itNext.idAwayTeam)
                ) { homeTeam: TeamEntityResponse, awayTeam: TeamEntityResponse ->

                    homeTeam.getResults()?.get(0)?.strTeamBadge?.let { listHome.add(it) }
                    awayTeam.getResults()?.get(0)?.strTeamBadge?.let { listAway.add(it) }
//                    listAway.add(awayTeam.strTeamBadge)
//                    listNext.add(itNext)
//                    setDate.add(itNext.dateEvent)
//                    listStadium.add(homeTeam.teams[0].strStadium)

                }

            }.doOnCompleted {
            }
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
                            it.strHomeGoalDetails,
                            it.strAwayTeam,
                            it.idAwayTeam,
                            it.intAwayScore,
                            it.strAwayGoalDetails,
                            it.intSpectators,
                            it.dateEvent,
                            it.strTime,
                            it.strEvent,
                            it.strLeague,
                            "",
                            ""
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