package com.mirzayogy.footballleague.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.model.LeagueResponse
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class MainAdapter(val context: Context, val leagues: ArrayList<LeagueResponse>) :
        RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(view: View) :
        RecyclerView.ViewHolder(view){

        private var leagueName: TextView = itemView.findViewById(MainUI.leagueName)
        private var leagueBadge: ImageView = itemView.findViewById(MainUI.leagueBadge)

        fun bindItems(league: LeagueResponse,context: Context){
            val logo = league.badge
            val resId : Int = context.resources.getIdentifier(logo,"drawable",context.packageName)
            Glide.with(itemView.context)
                .load(resId)
                .into(leagueBadge)

//            resId?.let { Picasso.get().load(it).fit().into(leagueBadge) }
            leagueName.text = league.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainUI().createView(AnkoContext.create(parent.context,parent)))
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItems(leagues[position],context)
    }
}

