package com.mirzayogy.footballleague.ui.matches

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.data.source.remote.response.LeagueResponse
import com.mirzayogy.footballleague.ui.matches.main.SectionsPagerAdapter
import com.mirzayogy.footballleague.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_matches.*
import org.jetbrains.anko.startActivity


class MatchesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)


        val leagueResponse: LeagueResponse?
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            leagueResponse = intent.getParcelableExtra("league")
            if (leagueResponse != null) {
                populateLeague(leagueResponse)
                val sectionsPagerAdapter = SectionsPagerAdapter(this, leagueResponse.id, supportFragmentManager)
                val viewPager: ViewPager = findViewById(R.id.view_pager)
                viewPager.adapter = sectionsPagerAdapter
                val tabs: TabLayout = findViewById(R.id.tabs)
                tabs.setupWithViewPager(viewPager)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false);
    }

    private fun populateLeague(leagueResponse: LeagueResponse) {
        separator.text = leagueResponse.name

        val resId: Int = resources.getIdentifier(leagueResponse.badge, "drawable", packageName)
        Glide.with(this)
            .load(resId)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(imgHome)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_menu, menu)

        val searchView = menu.findItem(R.id.search)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(eventKeyword: String): Boolean {
                startActivity<SearchActivity>("eventKeyword" to eventKeyword)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
        return true
    }
}