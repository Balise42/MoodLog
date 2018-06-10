package fr.pasithee.moodlog.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "moodEntry")
data class MoodEntryData(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                         @ColumnInfo(name = "date") var date: Date = Date(),
                         @ColumnInfo(name = "level") var level: Int = -1,
                         @Ignore var details: List<DetailData> = emptyList(),
                         @Ignore var occupations: List<OccupationData> = emptyList()
)