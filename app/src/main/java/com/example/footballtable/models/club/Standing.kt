package com.example.footballtable.models.club

data class Standing(
    val note: Note,
    val stats: List<Stat>,
    val team: Team
)