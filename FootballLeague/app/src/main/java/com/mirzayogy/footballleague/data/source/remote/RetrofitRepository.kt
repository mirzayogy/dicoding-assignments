package id.co.teknobara.sirintik.data.source.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRepository {
    fun create(): RetrofitServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .build()
        return retrofit.create(RetrofitServices::class.java)
    }
}