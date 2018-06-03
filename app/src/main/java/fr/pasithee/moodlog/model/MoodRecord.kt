package fr.pasithee.moodlog.model

import java.util.*

data class MoodRecord(
        var level: Int,
        val date: Date,
        var details: List<String>,
        var occupations: List<String>
)