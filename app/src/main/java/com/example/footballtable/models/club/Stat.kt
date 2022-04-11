package com.example.footballtable.models.club

data class Stat(
    val id: String,
    val name: String,
    val abbreviation: String,
    val description: String,
    val displayName: String,
    val displayValue: String,
    val shortDisplayName: String,
    val summary: String,
    val type: String,
    val value: Int
)