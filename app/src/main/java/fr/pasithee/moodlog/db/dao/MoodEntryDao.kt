package fr.pasithee.moodlog.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData
import java.util.*

@Dao
abstract class MoodEntryDao {
    @Insert
    abstract fun insertMood(moodEntry : MoodEntryData) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertDetails(details : List<DetailData>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertDetail(detail : DetailData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOccupation(occupation : OccupationData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertOccupations(occupations : List<OccupationData>)

    @Query("SELECT * FROM moodEntry where id =:id")
    abstract fun getMoodEntryData(id : Long) : MoodEntryData

    @Query("SELECT * FROM moodEntry where date != 0")
    abstract fun getMoodEntryData() : LiveData<List<MoodEntryData>>

    @Query("SELECT * FROM moodDetail where id =:id")
    abstract fun getDetailData(id : Long) : List<DetailData>

    @Query("SELECT * FROM occupation where id =:id")
    abstract fun getOccupationData(id : Long) : List<OccupationData>

    @Query("SELECT DISTINCT detail from moodDetail order by upper(detail)")
    abstract fun getDetailNames() : LiveData<List<String>>

    @Query("SELECT DISTINCT occupation from occupation order by upper(occupation)")
    abstract fun getOccupationNames(): LiveData<List<String>>

    @Query("DELETE from moodDetail where id = -1")
    abstract fun deleteUnusedDetails()

    @Query("DELETE from occupation where id = -1")
    abstract fun deleteUnusedOccupations()

    @Query("DELETE from moodDetail")
    abstract fun deleteMoodDetails()

    @Query("DELETE from occupation")
    abstract fun deleteOccupations()

    @Query("DELETE from moodEntry")
    abstract fun deleteMoodEntries()


    fun reinitDb() {
        deleteOccupations()
        deleteMoodDetails()
        deleteMoodEntries()
        insertMood(MoodEntryData(-1, Date(0)))
    }

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

    fun cleanUp() {
        deleteUnusedDetails()
        deleteUnusedOccupations()
    }

}