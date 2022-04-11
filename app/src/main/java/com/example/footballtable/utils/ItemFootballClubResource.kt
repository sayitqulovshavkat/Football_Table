package com.example.footballtable.utils

import com.example.footballtable.models.club.FootballClub


sealed class ItemFootballClubResource {

    object Loading : ItemFootballClubResource()

    data class Success(val list: ArrayList<FootballClub>) : ItemFootballClubResource()

    data class Error(val message: String) : ItemFootballClubResource()
}