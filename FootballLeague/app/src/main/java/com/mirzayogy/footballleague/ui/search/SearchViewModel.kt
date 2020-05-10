package com.mirzayogy.footballleague.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirzayogy.footballleague.data.source.remote.RetrofitRepository
import com.mirzayogy.footballleague.data.source.remote.RetrofitServices
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SearchViewModel : ViewModel() {

    private lateinit var retrofitServices: RetrofitServices
    private val compositeDisposable = CompositeDisposable()
    private lateinit var eventKeyword: String
    private val listResultEvents = MutableLiveData<ArrayList<EventResponse>>()


    fun setEventKeyword(eventKeyword:String){
        this.eventKeyword = eventKeyword
    }

    fun setResultEvent(){
        this.retrofitServices = RetrofitRepository.createRX()
        compositeDisposable.add(
            this.retrofitServices.searchEventRX(this.eventKeyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    listResultEvents.postValue(it.getResults())
                },{
                    Log.d("MRZ", "getEventsObservable: " + it.message)
                }
                )
        )
    }
    fun getResultEvent(): LiveData<ArrayList<EventResponse>> = listResultEvents

}
