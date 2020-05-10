package com.mirzayogy.footballleague.ui.matches.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.data.source.remote.response.EventResponse
import com.mirzayogy.footballleague.ui.matchesdetail.MatchesDetailActivity
import com.mirzayogy.footballleague.utils.DateHelper
import kotlinx.android.synthetic.main.card_matches.view.*
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.collections.ArrayList

class EventRecyclerAdapter : RecyclerView.Adapter<EventRecyclerAdapter.EventViewHolder>() {
    private val mData = ArrayList<EventResponse>()
    fun setData(items: ArrayList<EventResponse>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): EventViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_matches, viewGroup, false)
        return EventViewHolder(mView)
    }

    override fun onBindViewHolder(eventViewHolder: EventViewHolder, position: Int) {
        eventViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(eventResponse: EventResponse) {
            with(itemView){
                home_team.text = eventResponse.strHomeTeam
                away_team.text = eventResponse.strAwayTeam
                date_event.text = eventResponse.dateEvent?.let { DateHelper.customDate(it) }
                str_time.text = eventResponse.strTime
                home_score.text = eventResponse.intHomeScore?: "-"
                away_score.text = eventResponse.intAwayScore?: "-"

                setOnClickListener{
//                    Toast.makeText(context,eventResponse.strHomeTeam,Toast.LENGTH_SHORT).show()
                    context.startActivity<MatchesDetailActivity>("match" to eventResponse)
                }
            }
        }
    }
}