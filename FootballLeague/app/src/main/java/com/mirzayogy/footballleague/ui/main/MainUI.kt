package com.mirzayogy.footballleague.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class MainUI : AnkoComponent<ViewGroup>{
    companion object{
        val leagueBadge = 1
        val leagueName = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        verticalLayout(){
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

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
            }.lparams{
                margin = dip(16)
            }
        }
    }
}