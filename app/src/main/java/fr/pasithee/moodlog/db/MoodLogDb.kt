package fr.pasithee.moodlog.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import fr.pasithee.moodlog.db.dao.MoodEntryDao
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData
import fr.pasithee.moodlog.util.Converters

@Database(entities = [(MoodEntryData::class), (DetailData::class), (OccupationData::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoodLogDb : RoomDatabase() {

    companion object {
        @Volatile private var INSTANCE: MoodLogDb? = null

        fun getInstance(context: Context) : MoodLogDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, MoodLogDb::class.java, "moodlog.db").build()
    }

    abstract fun moodEntryDao() : MoodEntryDao
}