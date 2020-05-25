package com.mirzayogy.footballleague.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirzayogy.footballleague.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_matches.progressBar

class SearchActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        progressBar?.visibility  = View.VISIBLE

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false);

        searchAdapter = SearchAdapter()
        searchAdapter.notifyDataSetChanged()

       val eventKeyword = intent.getStringExtra("eventKeyword")

        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)

        if (eventKeyword != null) {
            searchViewModel.setEventKeyword(eventKeyword)
        }
        searchViewModel.setResultEvent()

        searchViewModel.getResultEvent().observe(this, Observer { eventResponse ->
            if (eventResponse != null) {
                searchAdapter.setData(eventResponse)
                searchRecyclerView.layoutManager = LinearLayoutManager(this)
                searchRecyclerView.adapter = searchAdapter
                progressBar?.visibility  = View.GONE

            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_menu, menu)

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(eventKeyword: String): Boolean {


                if (eventKeyword != null) {
                    searchViewModel.setEventKeyword(eventKeyword)
                }
                searchViewModel.setResultEvent()

                searchViewModel.getResultEvent().observe(this@SearchActivity, Observer { eventResponse ->
                    if (eventResponse != null) {
                        searchAdapter.setData(eventResponse)
                        searchRecyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
                        searchRecyclerView.adapter = searchAdapter
                        progressBar?.visibility  = View.GONE

                    }
                })

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
        return true
    }
}