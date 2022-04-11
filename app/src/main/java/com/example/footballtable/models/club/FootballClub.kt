package com.example.footballtable.models.club

import com.example.footballtable.models.club.Data
import java.io.Serializable

class FootballClub(
    val `data`: Data,
    val status: Boolean,
    var logo: String? = null
) : Serializable