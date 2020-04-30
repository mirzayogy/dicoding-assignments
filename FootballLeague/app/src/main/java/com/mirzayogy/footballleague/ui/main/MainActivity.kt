package com.mirzayogy.footballleague.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.model.LeagueResponse
import com.mirzayogy.footballleague.utils.JsonHelper
import org.jetbrains.anko.ctx
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val leagues: ArrayList<LeagueResponse> = JsonHelper(this@MainActivity).loadLeagues()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        verticalLayout{
            recyclerView{
                lparams(width = matchParent, height = matchParent)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MainAdapter(this@MainActivity, leagues)
            }
        }

//        leagues.clear()
//        leagues = JsonHelper(this).loadLeagues()
    }
}
