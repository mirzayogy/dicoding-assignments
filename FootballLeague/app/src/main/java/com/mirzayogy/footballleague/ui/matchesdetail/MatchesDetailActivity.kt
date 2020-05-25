package com.mirzayogy.footballleague.ui.matchesdetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.data.source.local.sqlite.MyDatabaseOpenHelper
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import com.mirzayogy.footballleague.utils.DateHelper
import kotlinx.android.synthetic.main.activity_matches_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class MatchesDetailActivity : AppCompatActivity() {

    lateinit var eventResponse:EventResponse
    private lateinit var matchesDetailViewModel: MatchesDetailViewModel
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var  id: Long = 0
    private val database: MyDatabaseOpenHelper
        get() = MyDatabaseOpenHelper.getInstance(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_detail)


        val intent = intent
        eventResponse = intent.getParcelableExtra("match")

        matchesDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MatchesDetailViewModel::class.java)


        id = eventResponse.idEvent
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

        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_top_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    EventResponse.TABLE_FAVORITE,
                    EventResponse.ID_EVENT to eventResponse.idEvent,
                    EventResponse.STR_HOME_TEAM to eventResponse.strHomeTeam,
                    EventResponse.ID_HOME_TEAM to eventResponse.idHomeTeam,
                    EventResponse.INT_HOME_SCORE to eventResponse.intHomeScore,
                    EventResponse.STR_HOME_GOAL_DETAILS to eventResponse.strHomeGoalDetails,
                    EventResponse.STR_AWAY_TEAM to eventResponse.strAwayTeam,
                    EventResponse.ID_AWAY_TEAM to eventResponse.idAwayTeam,
                    EventResponse.INT_AWAY_SCORE to eventResponse.intAwayScore,
                    EventResponse.STR_AWAY_GOAL_DETAILS to eventResponse.strAwayGoalDetails,
                    EventResponse.INT_SPECTATORS to eventResponse.intSpectators,
                    EventResponse.DATE_EVENT to eventResponse.dateEvent.toString(),
                    EventResponse.STR_TIME to eventResponse.strTime,
                    EventResponse.STR_EVENT to eventResponse.strEvent,
                    EventResponse.STR_LEAGUE to eventResponse.strLeague)
            }
            Toast.makeText(this,"Ditambahkan",Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,"Gagal",Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(EventResponse.TABLE_FAVORITE, "(ID_EVENT = {id})",
                    "id" to id)
            }
            Toast.makeText(this,"Dihapus",Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,"Gagal Hapus",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
    private fun favoriteState(){
        database.use {
            val result = select(EventResponse.TABLE_FAVORITE)
                .whereArgs("(ID_EVENT = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<EventResponse>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }
}