package com.mirzayogy.footballleague.data.source.remote

import com.mirzayogy.footballleague.data.source.local.entity.EventEntityResponse
import com.mirzayogy.footballleague.data.source.local.entity.TeamEntityResponse
import retrofit2.Call
import retrofit2.http.*
import rx.Observable
import kotlin.collections.ArrayList

interface RetrofitServices {
    @GET("eventsnextleague.php?")
    fun getNextEvent(@Query("id") idLeague: String): Call<EventEntityResponse>

    @GET("eventspastleague.php?")
    fun getLastEvent(@Query("id") idLeague: String): Call<EventEntityResponse>

    @GET("lookupteam.php?")
    fun getTeamDetail(@Query("id") idTeam: String): Call<EventEntityResponse>

    @GET("lookupevent.php?id=441613")
    fun getEventDetail(@Query("id") idEvent: String): Call<EventEntityResponse>

    @GET("https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=juventus")
    fun searchEvent(@Query("e") event: String): Call<EventEntityResponse>

    @GET("eventsnextleague.php?")
    fun getNextEventRX(@Query("id") idLeague: String): Observable<EventEntityResponse>

    @GET("lookupteam.php?")
    fun getTeamDetailRX(@Query("id") idTeam: String): Observable<TeamEntityResponse>


}