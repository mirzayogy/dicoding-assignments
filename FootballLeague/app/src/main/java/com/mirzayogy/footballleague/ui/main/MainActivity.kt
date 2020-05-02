package com.mirzayogy.footballleague.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.model.LeagueResponse
import com.mirzayogy.footballleague.ui.detail.DetailActivity
import com.mirzayogy.footballleague.utils.JsonHelper
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val leagues: ArrayList<LeagueResponse> = JsonHelper(this@MainActivity).loadLeagues()

        super.onCreate(savedInstanceState)

        verticalLayout{
            recyclerView{
                lparams(width = matchParent, height = matchParent)
                padding = dip(16)

                layoutManager = GridLayoutManager(this@MainActivity,2)
                adapter = MainAdapter(this@MainActivity, leagues){
                    startActivity<DetailActivity>("league" to it)
                }
            }
        }
    }
}
