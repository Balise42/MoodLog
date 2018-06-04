package fr.pasithee.moodlog.db.dao

import android.arch.persistence.room.*
import fr.pasithee.moodlog.db.entities.MoodEntryData

@Dao
interface MoodEntryDao {
    @get:Query("SELECT * from moodEntry")
    val all: List<MoodEntryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry : MoodEntryData)

    @Delete
    fun delete(entry : MoodEntryData)
}