package com.example.footballtable.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballtable.repositories.FootballRepository
import com.example.footballtable.utils.NetworkHelper

class ViewModelFactory(
    private val footballRepository: FootballRepository,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FootballViewModel::class.java)) {
            return FootballViewModel(footballRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error")
    }
}