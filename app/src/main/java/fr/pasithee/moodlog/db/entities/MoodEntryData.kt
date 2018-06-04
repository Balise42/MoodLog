package fr.pasithee.moodlog.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "moodEntry")
data class MoodEntryData(@PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name="date") var date: Date,
        @ColumnInfo(name="level") var level: Int
)