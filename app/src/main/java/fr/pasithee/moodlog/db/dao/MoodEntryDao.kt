package fr.pasithee.moodlog.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData

@Dao
abstract class MoodEntryDao {
    @Insert
    abstract fun insertMood(moodEntry : MoodEntryData) : Long

    @Insert
    abstract fun insertDetails(details : List<DetailData>)

    @Insert
    abstract fun insertOccupations(occupations : List<OccupationData>)

    @Query("SELECT * FROM moodEntry where id =:id")
    abstract fun getMoodEntryData(id : Long) : MoodEntryData

    @Query("SELECT * FROM moodDetail where id =:id")
    abstract fun getDetailData(id : Long) : List<DetailData>

    @Query("SELECT * FROM occupation where id =:id")
    abstract fun getOccupationData(id : Long) : List<OccupationData>

    @Query("SELECT DISTINCT detail from moodDetail order by detail")
    abstract fun getDetailNames() : LiveData<List<String>>

    fun insertMoodAllData(mood : MoodEntryData) {
        mood.id = insertMood(mood)
        val details = mood.details
        for (detail in details) {
            detail.id = mood.id
        }
        insertDetails(details)

        val occupations = mood.occupations
        for (occupation in occupations) {
            occupation.id = mood.id
        }
        insertOccupations(occupations)
    }

    fun getMoodAllData(id : Long) : MoodEntryData {
        val mood = getMoodEntryData(id)
        mood.details = getDetailData(id)
        mood.occupations = getOccupationData(id)
        return mood
    }
}