package com.mirzayogy.footballleague.ui.matches.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.ui.matches.MatchesViewModel
import kotlinx.android.synthetic.main.fragment_matches.*

class PlaceholderFragment : Fragment() {

    private var progressBar: ProgressBar? = null
    private lateinit var matchesViewModel: MatchesViewModel
    private lateinit var eventRecyclerAdapter: EventRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchesViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MatchesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_matches, container, false)
        progressBar = root.findViewById(R.id.progressBar)
        progressBar?.visibility  = View.VISIBLE

        showLoading(true)

        eventRecyclerAdapter = EventRecyclerAdapter()
        eventRecyclerAdapter.notifyDataSetChanged()

        val idLeague = arguments?.getString(ID_LEAGUE)
        val section = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1

        if (idLeague != null) {
            matchesViewModel.setSelectedLeague(idLeague)
        }
        matchesViewModel.setNextEvent()
        matchesViewModel.setLastEvent()
        matchesViewModel.setFavoriteEvent(context)

        when (section) {
            1 -> {
                matchesViewModel.getNextEvent().observe(viewLifecycleOwner, Observer { eventResponse ->
                    if (eventResponse != null) {
                        eventRecyclerAdapter.setData(eventResponse)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = eventRecyclerAdapter
                        showLoading(false)
                    }
                })
            }
            2 -> {
                matchesViewModel.getLastEvent().observe(viewLifecycleOwner, Observer { eventResponse ->
                    if (eventResponse != null) {
                        eventRecyclerAdapter.setData(eventResponse)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = eventRecyclerAdapter
                        showLoading(false)
                    }
                })
            }
            else -> {

                matchesViewModel.getFavoriteEvent().observe(viewLifecycleOwner, Observer { eventResponse ->
                    if (eventResponse != null) {
                        eventRecyclerAdapter.setData(eventResponse)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = eventRecyclerAdapter
                        showLoading(false)
                    }
                })
            }
        }



        return root
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ID_LEAGUE = "id_league"

        @JvmStatic
        fun newInstance(sectionNumber: Int, idLeague:String): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ID_LEAGUE, idLeague)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        matchesViewModel.setFavoriteEvent(context)
    }
}