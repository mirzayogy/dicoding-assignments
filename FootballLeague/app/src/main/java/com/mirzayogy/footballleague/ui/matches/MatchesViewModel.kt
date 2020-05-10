package com.mirzayogy.footballleague.ui.matches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.data.source.local.entity.EventEntityResponse
import com.mirzayogy.footballleague.data.source.remote.RetrofitServices
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import com.mirzayogy.footballleague.data.source.remote.RetrofitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchesViewModel : ViewModel() {
    private lateinit var leagueId: String
    private val listNextEvents = MutableLiveData<ArrayList<EventResponse>>()
    private val listLastEvents = MutableLiveData<ArrayList<EventResponse>>()
    private lateinit var retrofitServices : RetrofitServices
    private val compositeDisposable = CompositeDisposable()


    fun setSelectedLeague(leagueId:String){
        this.leagueId = leagueId
    }

    fun setNextEvent(){
        this.retrofitServices = RetrofitRepository.createRX()
        compositeDisposable.add(
            this.retrofitServices.getNextEventRX(this.leagueId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    listNextEvents.postValue(it.getResults())
                },{
                    Log.d("MRZ", "getEventsObservable: " + it.message)
                }
                )
        )
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
                            it.strLeague
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
