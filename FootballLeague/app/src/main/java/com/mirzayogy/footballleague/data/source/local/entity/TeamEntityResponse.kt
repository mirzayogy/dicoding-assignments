package com.mirzayogy.footballleague.data.source.local.entity

import com.google.gson.annotations.SerializedName
import com.mirzayogy.footballleague.data.source.remote.response.TeamResponse

class TeamEntityResponse {
    @SerializedName("events")
    private val results: ArrayList<TeamResponse>? = null

    fun getResults() : ArrayList<TeamResponse>?{
        return results
    }
}