package com.mirzayogy.footballleague.ui.matches.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mirzayogy.footballleague.R

private val TAB_TITLES = arrayOf(
    R.string.next_match,
    R.string.previous_match
)

class SectionsPagerAdapter(private val context: Context, private val idLeague: String, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position + 1, idLeague)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}