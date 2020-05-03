package id.co.teknobara.sirintik.data.source.remote

import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("eventsnextleague.php?")
    fun getPangan(@Query("id") idEvent: String): Call<EventResponse>

}