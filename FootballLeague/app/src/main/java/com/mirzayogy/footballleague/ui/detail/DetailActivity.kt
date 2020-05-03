package com.mirzayogy.footballleague.ui.detail

import android.graphics.Typeface
import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mirzayogy.footballleague.data.source.remote.response.LeagueResponse
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    private val leagueBadge = 1
    private val leagueName = 2
    private val leagueDescription = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            topPadding = 20
            gravity = Gravity.CENTER_HORIZONTAL

            imageView {
                id = leagueBadge
            }.lparams {
                height = dip(150)
                width = dip(150)
                margin = dip(16)
            }

            textView {
                id = leagueName
                textSize = 24F
                typeface = Typeface.DEFAULT_BOLD
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams {
                width = matchParent
                margin = dip(16)
            }

            scrollView {

                textView {
                    id = leagueDescription
                    textSize = 16F
                }.lparams {
                    width = matchParent
                    margin = dip(16)
                }
            }
        }

        val intent = intent
        val leagueResponse: LeagueResponse?

        leagueResponse = intent.getParcelableExtra("league")

        val leagueBadgeIV: ImageView = findViewById(leagueBadge)
        val leagueNameTV: TextView = findViewById(leagueName)
        val leagueDescriptionTV: TextView = findViewById(leagueDescription)

        val logo = leagueResponse.badge
        val resId: Int = resources.getIdentifier(logo, "drawable", packageName)

        Glide.with(this@DetailActivity)
            .load(resId)
            .into(leagueBadgeIV)

        leagueNameTV.text = leagueResponse.name
        leagueDescriptionTV.text = leagueResponse.description
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            leagueDescriptionTV.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}