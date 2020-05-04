package id.co.teknobara.sirintik.data.source.remote

import com.mirzayogy.footballleague.data.source.local.entity.EventEntityResponse
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("eventsnextleague.php?")
    fun getNextEvent(@Query("id") idEvent: String): Call<EventEntityResponse>

    @GET("lookupteam.php?id=133604")
    fun getTeamDetail(@Query("id") idEvent: String): Call<EventEntityResponse>

    @GET("lookupevent.php?id=441613")
    fun getEventDetail(@Query("id") idEvent: String): Call<EventEntityResponse>

    @GET("https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=juventus")
    fun searchEvent(@Query("e") event: String): Call<EventEntityResponse>


}