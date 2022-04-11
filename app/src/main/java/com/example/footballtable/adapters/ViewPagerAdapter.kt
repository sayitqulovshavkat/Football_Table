package com.example.footballtable.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballtable.ui.fragment.MatchesFragment
import com.example.footballtable.ui.fragment.TableFragment
import com.example.footballtable.models.club.FootballClub

class ViewPagerAdapter(fragment: Fragment, val footballClub: FootballClub) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MatchesFragment.newInstance(footballClub)
            }
            else -> {
                TableFragment.newInstance(footballClub)
            }
        }
    }
}