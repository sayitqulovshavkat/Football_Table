package com.example.footballtable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.footballtable.models.club.FootballClub
import com.example.footballtable.repositories.FootballRepository
import com.example.footballtable.utils.ItemFootballClubResource
import com.example.footballtable.utils.NetworkHelper

class FootballViewModel(
    private val footballRepository: FootballRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val stateFlow =
        MutableStateFlow<ItemFootballClubResource>(ItemFootballClubResource.Loading)

    init {
        fetchFootball()
    }

    fun fetchFootball(): StateFlow<ItemFootballClubResource> {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                footballRepository.getAllLeagues()
                    .catch {
                        stateFlow.emit(ItemFootballClubResource.Error(it.message ?: ""))
                    }
                    .collect {
                        if (it.isSuccessful) {
                            val leaguesData1 = it.body()
                            val data = ArrayList(leaguesData1?.data ?: emptyList())
                            val footBallClubList = ArrayList<FootballClub>()
                            data.forEach { d ->
                                footballRepository.getItemFootballClub(d.id)
                                    .catch {

                                    }
                                    .collect { res ->
                                        if (res.isSuccessful) {
                                            val body = res.body()
                                            if (body != null) {
                                                body.logo = d.logos.light
                                                footBallClubList.add(body)
                                            }
                                        }
                                    }
                            }
                            stateFlow.emit(ItemFootballClubResource.Success(footBallClubList))
                        } else {
                            stateFlow.emit(ItemFootballClubResource.Error("Client or Server error!"))
                        }
                    }
            }
        }
        return stateFlow
    }
}

