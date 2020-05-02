package com.mirzayogy.footballleague.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.model.LeagueResponse
import com.mirzayogy.footballleague.ui.detail.DetailActivity
import com.mirzayogy.footballleague.ui.matches.MatchesActivity
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
//
                    val choices = listOf("Detail League", "Matches")
                    selector("Content that you want to see", choices) { _, i ->
                        when (i) {
                            0 -> startActivity<DetailActivity>("league" to it)
                            1 -> startActivity<MatchesActivity>("league" to it)
                        }
                    }
                }
            }
        }
    }
}
