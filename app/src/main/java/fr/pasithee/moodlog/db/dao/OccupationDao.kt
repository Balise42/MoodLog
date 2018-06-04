package fr.pasithee.moodlog.db.dao

import android.arch.persistence.room.*
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.OccupationData

@Dao
interface OccupationDao {
    @get:Query("SELECT * from occupation")
    val all: List<DetailData>

    @get:Query("SELECT DISTINCT occupation from occupation")
    val allDetails: List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entry : OccupationData)

    @Delete
    fun delete(entry : OccupationData)
}