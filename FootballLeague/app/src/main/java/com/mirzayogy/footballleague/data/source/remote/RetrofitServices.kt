package com.mirzayogy.footballleague.data.source.remote

import com.mirzayogy.footballleague.data.source.local.entity.EventEntityResponse
import com.mirzayogy.footballleague.data.source.local.entity.EventSearchResponse
import com.mirzayogy.footballleague.data.source.local.entity.TeamEntityResponse
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import com.mirzayogy.footballleague.data.source.remote.response.TeamResponse
import retrofit2.Call
import retrofit2.http.*
import io.reactivex.Observable;
import io.reactivex.Single
import kotlin.collections.ArrayList

interface RetrofitServices {

    @GET("eventspastleague.php?")
    fun getLastEvent(@Query("id") idLeague: String): Call<EventEntityResponse>

    @GET("eventsnextleague.php?")
    fun getNextEventRX(@Query("id") idLeague: String): Observable<EventEntityResponse>

    @GET("searchevents.php?")
    fun searchEventRX(@Query("e") event: String): Observable<EventSearchResponse>


    @GET("lookupteam.php?")
    fun getTeamDetailRX(@Query("id") idTeam: String?): Observable<TeamEntityResponse>

}