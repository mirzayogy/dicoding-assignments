package com.mirzayogy.footballleague.ui.main

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.R.*
import com.mirzayogy.footballleague.model.LeagueResponse
import org.jetbrains.anko.*
import com.mirzayogy.footballleague.ui.detail.DetailActivity as DetailActivity

class MainAdapter(private val context: Context, private val leagues: ArrayList<LeagueResponse>, private val listener: (LeagueResponse) -> Unit) :
        RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    class MainViewHolder(view: View) :
        RecyclerView.ViewHolder(view){

        private var leagueName: TextView = itemView.findViewById(MainUI.leagueName)
        private var leagueBadge: ImageView = itemView.findViewById(MainUI.leagueBadge)

        fun bindItems(league: LeagueResponse,context: Context, listener: (LeagueResponse) -> Unit){
            val logo = league.badge
            val resId : Int = context.resources.getIdentifier(logo,"drawable",context.packageName)
            Glide.with(itemView.context)
                .load(resId)
                .into(leagueBadge)
            leagueName.text = league.name

            itemView.setOnClickListener{listener(league)}

        }
    }

    class MainUI : AnkoComponent<ViewGroup>{
        companion object{
            val leagueBadge = 1
            val leagueName = 2
        }

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
            verticalLayout{
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                backgroundColor = ContextCompat.getColor(context, color.colorCard)
                gravity = Gravity.CENTER

                imageView{
//                        R.id.league_badge
                    id = leagueBadge
                }.lparams{
                    height = dip(75)
                    width = dip(75)
                }

                textView{
                    id = leagueName
                    textSize = 16f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams{
                    margin = dip(16)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(MainUI().createView(AnkoContext.create(parent.context,parent)))
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItems(leagues[position],context,listener)
    }
}

