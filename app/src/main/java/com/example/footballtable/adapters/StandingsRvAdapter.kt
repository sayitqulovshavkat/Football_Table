package com.example.footballtable.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballtable.databinding.RvItemBinding
import com.squareup.picasso.Picasso
import com.example.footballtable.models.club.FootballClub
import com.example.footballtable.models.club.Standing

class StandingsRvAdapter(
    private val list: ArrayList<FootballClub>,
    val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<StandingsRvAdapter.HomeVh>() {

    private lateinit var clubRvAdapter: ClubRvAdapter

    inner class HomeVh(private val rvItemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(rvItemBinding.root) {
        fun onBind(footballClub: FootballClub) {
            rvItemBinding.apply {

                Picasso.get()
                    .load(footballClub.logo)
                    .into(countryFlagImg)

                if (footballClub.data.abbreviation == "A Lge") {
                    countryFlagImg.setBackgroundColor(Color.WHITE)
                }

                leagueNameTv.text =
                    footballClub.data.name.substring(footballClub.data.name.indexOf(" ") + 1)
                countryTv.text =
                    footballClub.data.name.substring(0, footballClub.data.name.indexOf(" "))
                clubRvAdapter = ClubRvAdapter(footballClub.data.standings as ArrayList<Standing>)
                innerTableRv.adapter = clubRvAdapter
            }

            rvItemBinding.root.setOnClickListener {
                itemClickListener.onItemClick(footballClub)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVh {
        return HomeVh(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeVh, position: Int) {
        return holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(footballClub: FootballClub)
    }
}