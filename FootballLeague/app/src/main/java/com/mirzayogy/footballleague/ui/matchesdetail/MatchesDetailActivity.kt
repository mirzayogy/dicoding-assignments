package com.mirzayogy.footballleague.ui.matchesdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import kotlinx.android.synthetic.main.activity_matches_detail.*
import androidx.lifecycle.Observer
import com.mirzayogy.footballleague.utils.DateHelper
import kotlinx.android.synthetic.main.card_matches.view.*


class MatchesDetailActivity : AppCompatActivity() {

    lateinit var eventResponse:EventResponse
    private lateinit var matchesDetailViewModel: MatchesDetailViewModel

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
}