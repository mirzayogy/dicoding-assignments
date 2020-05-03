package com.mirzayogy.footballleague.ui.matches

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.data.source.remote.response.LeagueResponse
import com.mirzayogy.footballleague.ui.matches.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_matches.*


class MatchesActivity : AppCompatActivity() {

    private lateinit var matchesViewModel: MatchesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

//        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]

        val leagueResponse: LeagueResponse?
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            leagueResponse = intent.getParcelableExtra("league")
            if (leagueResponse != null) {
//                viewModel.setSelectedCourse(courseId)
//                val modules = viewModel.getModules()
//                adapter.setModules(modules)
                populateLeague(leagueResponse)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun populateLeague(leagueResponse: LeagueResponse) {
        leagueName.text = leagueResponse.name
        leagueDescription.text = leagueResponse.description
        leagueDescription.movementMethod = ScrollingMovementMethod()

        val resId: Int = resources.getIdentifier(leagueResponse.badge, "drawable", packageName)
        Glide.with(this)
            .load(resId)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(leagueBadge)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.search -> {
                Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show()
            }
        }
    }
}