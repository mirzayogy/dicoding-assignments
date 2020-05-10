package com.mirzayogy.footballleague.data.source.remote

import com.mirzayogy.footballleague.data.source.local.entity.EventEntityResponse
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

    @GET("lookupteam.php?")
    fun getTeamDetail(@Query("id") idTeam: String?): Call<TeamResponse>

    @GET("lookupevent.php?id=441613")
    fun getEventDetail(@Query("id") idEvent: String): Call<EventEntityResponse>

    @GET("https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=juventus")
    fun searchEvent(@Query("e") event: String): Call<EventEntityResponse>



    @GET("lookupteam.php?")
    fun getTeamDetailRX(@Query("id") idTeam: String?): Observable<TeamEntityResponse>

    @GET("eventsnextleague.php?")
    fun getNextEventSingleRX(@Query("id") idLeague: String): ArrayList<EventResponse>

    @GET("lookupteam.php?")
    fun getTeamDetailSingleRX(@Query("id") idTeam: String): Single<TeamResponse>


}