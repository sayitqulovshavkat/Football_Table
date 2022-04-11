package com.example.footballtable.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballtable.R
import com.example.footballtable.databinding.ClubRvItemBinding
import com.squareup.picasso.Picasso
import com.example.footballtable.models.club.Standing

class ClubRvAdapter(private val standingList: ArrayList<Standing>) :
    RecyclerView.Adapter<ClubRvAdapter.TableVh>() {

    inner class TableVh(private val clubRvItemBinding: ClubRvItemBinding) :
        RecyclerView.ViewHolder(clubRvItemBinding.root) {
        fun onBind(standing: Standing) {
            clubRvItemBinding.apply {

                Picasso.get()
                    .load(standing.team.logos[0].href)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(clubFlagImg)

                clubNameTv.text = standing.team.name

                standing.stats.forEach {
                    when (it.abbreviation) {
                        "W" -> {
                            wins.text = it.value.toString()
                        }
                        "L" -> {
                            losses.text = it.value.toString()
                        }
                        "D" -> {
                            draw.text = it.value.toString()
                        }
                        "GP" -> {
                            gamePlayed.text = it.value.toString()
                        }
                        "P" -> {
                            pts.text = it.value.toString()
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableVh {
        return TableVh(
            ClubRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TableVh, position: Int) {
        holder.onBind(standingList[position])
    }

    override fun getItemCount(): Int {
        return 4
    }
}