package com.mirzayogy.footballleague.ui.matchesdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.data.source.remote.RetrofitRepository
import com.mirzayogy.footballleague.data.source.remote.RetrofitServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchesDetailViewModel : ViewModel() {

    private lateinit var retrofitServices : RetrofitServices
    private val compositeDisposable = CompositeDisposable()
    private lateinit var idTeam: String
    private var strHomeBadge= MutableLiveData<String>()
    private var strAwayBadge= MutableLiveData<String>()

    fun setStrHomeBadge(idTeam:String){
        this.retrofitServices = RetrofitRepository.createRX()
        compositeDisposable.add(
            this.retrofitServices.getTeamDetailRX(idTeam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if(it.getResults()!=null) {
                        strHomeBadge.postValue(it.getResults()!![0].strTeamBadge)
                    }
                },{
                    Log.d("MRZ", it.message)
                }
                )
        )
    }
    fun setStrAwayBadge(idTeam:String){
        this.retrofitServices = RetrofitRepository.createRX()
        compositeDisposable.add(
            this.retrofitServices.getTeamDetailRX(idTeam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if(it.getResults()!=null) {
                        strAwayBadge.postValue(it.getResults()!![0].strTeamBadge)
                    }
                },{
                    Log.d("MRZ", it.message)
                }
                )
        )
    }

    fun getStrHomeBadge(): LiveData<String> = strHomeBadge
    fun getStrAwayBadge(): LiveData<String> = strAwayBadge

}