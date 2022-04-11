package com.example.footballtable.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.footballtable.models.club.FootballClub
import com.example.footballtable.models.leagues.LeaguesData

interface ApiService {

    @GET("leagues")
    suspend fun getAllLeagues(): Response<LeaguesData>

    @GET("leagues/{id}/standings")
    suspend fun getItemFootballClub(
        @Path("id") id: String,
        @Query("season") season: String = "2022",
        @Query("sort") sort: String = "asc"
    ): Response<FootballClub>
}