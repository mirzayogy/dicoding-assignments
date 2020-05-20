package com.mirzayogy.footballleague.ui.matchesdetail

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import kotlinx.android.synthetic.main.activity_matches_detail.*
import androidx.lifecycle.Observer
import com.mirzayogy.footballleague.data.source.local.sqlite.Favorite
import com.mirzayogy.footballleague.data.source.local.sqlite.MyDatabaseOpenHelper
import com.mirzayogy.footballleague.utils.DateHelper
import org.jetbrains.anko.db.insert
import kotlinx.android.synthetic.main.card_matches.view.*


class MatchesDetailActivity : AppCompatActivity() {

    lateinit var eventResponse:EventResponse
    private lateinit var matchesDetailViewModel: MatchesDetailViewModel
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private val database: MyDatabaseOpenHelper
        get() = MyDatabaseOpenHelper.getInstance(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_detail)

        val intent = intent
        eventResponse = intent.getParcelableExtra("match")

        matchesDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MatchesDetailViewModel::class.java)



        str_event.text = eventResponse.dateEvent?.let { DateHelper.customDate(it) }
        str_league.text = eventResponse.strLeague
        str_home.text = eventResponse.strHomeTeam
        str_away.text = eventResponse.strAwayTeam

        int_home_score.text = eventResponse.intHomeScore?: "-"
        int_away_score.text = eventResponse.intAwayScore?: "-"

        str_home_goal_details.text = eventResponse.strHomeGoalDetails?: "-"
        str_away_goal_details.text = eventResponse.strAwayGoalDetails?: "-"

        int_spectators.text = eventResponse.intSpectators?: "-"


        matchesDetailViewModel.setStrHomeBadge(eventResponse.idHomeTeam)
        matchesDetailViewModel.getStrHomeBadge().observe(this, Observer{
            Glide.with(this)
                .load(it)
            .apply(RequestOptions().placeholder(R.drawable.ic_loading))
                .into(imgHome)
        })

        matchesDetailViewModel.setStrAwayBadge(eventResponse.idAwayTeam)
        matchesDetailViewModel.getStrAwayBadge().observe(this, Observer{
            Glide.with(this)
                .load(it)
                .apply(RequestOptions().placeholder(R.drawable.ic_loading))
                .into(imgAway)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_top_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                addToFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.ID_EVENT to eventResponse.idEvent,
                    Favorite.STR_HOME_TEAM to eventResponse.strHomeTeam,
                    Favorite.ID_HOME_TEAM to eventResponse.idHomeTeam,
                    Favorite.INT_HOME_SCORE to eventResponse.intHomeScore,
                    Favorite.STR_HOME_GOAL_DETAILS to eventResponse.strHomeGoalDetails,
                    Favorite.STR_AWAY_TEAM to eventResponse.strAwayTeam,
                    Favorite.ID_AWAY_TEAM to eventResponse.idAwayTeam,
                    Favorite.INT_AWAY_SCORE to eventResponse.intAwayScore,
                    Favorite.STR_AWAY_GOAL_DETAILS to eventResponse.strAwayGoalDetails,
                    Favorite.INT_SPECTATORS to eventResponse.intSpectators,
                    Favorite.DATE_EVENT to eventResponse.dateEvent.toString(),
                    Favorite.STR_TIME to eventResponse.strTime,
                    Favorite.STR_EVENT to eventResponse.strEvent,
                    Favorite.STR_LEAGUE to eventResponse.strLeague)
            }
            Toast.makeText(this,"Ditambahkan",Toast.LENGTH_SHORT).show()
//            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,"Gagal",Toast.LENGTH_SHORT).show()

//            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }
}