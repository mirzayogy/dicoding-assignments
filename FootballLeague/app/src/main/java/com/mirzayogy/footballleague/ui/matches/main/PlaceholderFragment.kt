package com.mirzayogy.footballleague.ui.matches.main

import android.os.Bundle
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.ui.matches.MatchesViewModel
import kotlinx.android.synthetic.main.fragment_matches.*

class PlaceholderFragment : Fragment() {

    private var progressBar: ProgressBar? = null
    private lateinit var pageViewModel: PageViewModel
    private lateinit var matchesViewModel: MatchesViewModel
    private lateinit var eventRecyclerAdapter: EventRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        matchesViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MatchesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_matches, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        progressBar = root.findViewById(R.id.progressBar)
        progressBar?.visibility  = View.VISIBLE
        pageViewModel.text.observe(viewLifecycleOwner, Observer<String> {
            textView.text = it
        })

        showLoading(true)

        eventRecyclerAdapter = EventRecyclerAdapter()
        eventRecyclerAdapter.notifyDataSetChanged()



        matchesViewModel.setSelectedLeague("4328")
        matchesViewModel.setNextEvent()
        matchesViewModel.getNextEvent().observe(viewLifecycleOwner, Observer { eventResponse ->
            if (eventResponse != null) {
                eventRecyclerAdapter.setData(eventResponse)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = eventRecyclerAdapter
                showLoading(false)
            }
        })


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
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}