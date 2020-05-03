package com.mirzayogy.footballleague.data.source.local.entity

import com.google.gson.annotations.SerializedName
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse

class EventEntityResponse {
    @SerializedName("events")
    private val results: ArrayList<EventResponse>? = null

    fun getResults() : ArrayList<EventResponse>?{
        return results
    }
}