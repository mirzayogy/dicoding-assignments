package id.co.teknobara.sirintik.data.source.remote

import com.google.gson.GsonBuilder
import com.mirzayogy.footballleague.data.source.remote.RetrofitServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

object RetrofitRepository {
    fun create(): RetrofitServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .build()
        return retrofit.create(RetrofitServices::class.java)
    }

    fun createRX(): RetrofitServices {
        val gson = GsonBuilder().create()
        val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .build()
        return retrofit.create(RetrofitServices::class.java)
    }
}