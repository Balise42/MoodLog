package fr.pasithee.moodlog.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData
import java.util.*

data class MoodRecord(
        @Embedded var moodRecord: MoodEntryData = MoodEntryData(null, Date(), -1),
        @Relation(
                parentColumn = "id",
                entity = DetailData::class,
                entityColumn = "id",
                projection = arrayOf("detail")
        ) var details: List<String> = listOf(),
        @Relation(
                parentColumn = "id",
                entity = OccupationData::class,
                entityColumn = "id",
                projection = arrayOf("occupation")
        ) var occupations: List<String> = listOf()
)