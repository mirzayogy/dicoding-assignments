package com.mirzayogy.footballleague.ui.detail

import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.mirzayogy.footballleague.R
import com.mirzayogy.footballleague.model.LeagueResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class DetailActivity : AppCompatActivity() {

//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View group
        verticalLayout {
            topPadding = 20
            gravity = Gravity.CENTER_HORIZONTAL
            // TextView untuk menampilkan hasil penghitungan Umur
            var resultAge = textView {
                text = "0"
                textSize = 24F
                typeface = Typeface.DEFAULT_BOLD
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams {
                width = matchParent
            }
            // EditText utuk Input tahun lahir
            val birthYear = editText {
                hint = "Enter year"
            }.lparams {
                width = matchParent
                height = wrapContent
                gravity = Gravity.CENTER
                leftMargin = 10
                rightMargin = 10
            }
            // Button untuk Triger penghitungan Umur
            val btnFind = button {
                text = "Find My Age !"
                // Memberi Event OnClick pada button
                onClick {
                    // Dapatkan tahun yang di inputkanz
                    val year = 20
                    // Dapatkan tahun sekarang, kurangi dngan variable year
                    val age = Calendar.getInstance().get(Calendar.YEAR) - year;
                    // Set hasil umur ke TextView
                    resultAge.text = age.toString()
                }
            }.lparams {
                topMargin = 10
                width = wrapContent
            }
        }

        val intent = intent
        var leagueResponse: LeagueResponse
        leagueResponse = intent.getParcelableExtra("league")
        Toast.makeText(this@DetailActivity,leagueResponse.name,Toast.LENGTH_LONG).show()
    }
}