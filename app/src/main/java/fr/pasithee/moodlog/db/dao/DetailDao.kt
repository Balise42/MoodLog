package fr.pasithee.moodlog.db.dao

import android.arch.persistence.room.*
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData

@Dao
interface DetailDao {
    @get:Query("SELECT * from moodDetail")
    val all: List<DetailData>

    @get:Query("SELECT DISTINCT detail from moodDetail")
    val allDetails: List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entry : DetailData)

    @Delete
    fun delete(entry : DetailData)
}