package com.example.footballtable.repositories

import kotlinx.coroutines.flow.flow
import com.example.footballtable.network.ApiService

class FootballRepository(private val apiService: ApiService) {

    suspend fun getAllLeagues() = flow { emit(apiService.getAllLeagues()) }

    suspend fun getItemFootballClub(id: String) = flow { emit(apiService.getItemFootballClub(id)) }
}