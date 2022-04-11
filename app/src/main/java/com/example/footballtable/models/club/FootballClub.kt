package com.example.footballtable.models.club

import java.io.Serializable

class FootballClub(
    val data: Data,
    val status: Boolean,
    var logo: String? = null
) : Serializable