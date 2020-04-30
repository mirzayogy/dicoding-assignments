package com.mirzayogy.footballleague.utils

import android.content.Context
import com.mirzayogy.footballleague.model.LeagueResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadLeagues(): ArrayList<LeagueResponse> {
        val list = ArrayList<LeagueResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("league.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val league = listArray.getJSONObject(i)

                val id = league.getString("id")
                val name = league.getString("name")
                val description = league.getString("description")
                val badge = league.getString("badge")

                val leagueResponse = LeagueResponse(id, name, description, badge)
                list.add(leagueResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}