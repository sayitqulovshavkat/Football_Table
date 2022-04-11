package com.example.footballtable.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.footballtable.R
import com.example.footballtable.models.club.FootballClub

private const val ARG_PARAM1 = "param1"


class MatchesFragment : Fragment() {

    private var param1: FootballClub? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { param1 = it.getSerializable(ARG_PARAM1) as FootballClub? }
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: FootballClub) =
            MatchesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}