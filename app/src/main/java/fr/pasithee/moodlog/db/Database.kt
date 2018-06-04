package fr.pasithee.moodlog.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import fr.pasithee.moodlog.db.dao.MoodEntryDao
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData
import fr.pasithee.moodlog.db.utils.Converters

@Database(entities = arrayOf(MoodEntryData::class, DetailData::class, OccupationData::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun MoodEntryDao() : MoodEntryDao
}