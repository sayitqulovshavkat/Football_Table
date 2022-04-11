package com.example.footballtable.utils

import com.example.footballtable.models.leagues.Data


sealed class FootballResource {

    object Loading: FootballResource()

    data class Success(val list:List<Data>): FootballResource()

    data class Error(val message:String): FootballResource()
}