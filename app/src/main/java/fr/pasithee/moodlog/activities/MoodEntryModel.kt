package fr.pasithee.moodlog.activities

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.os.AsyncTask
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData
import java.util.*

class MoodEntryModel : ViewModel() {
    lateinit var moodEntry : MoodEntryData

    fun createMoodEntry() {
        moodEntry = MoodEntryData(0, Date(), -1, emptyList(), emptyList())
    }

    fun setLevel(level : Int) {
        moodEntry.level = level
    }

    fun setDetails(details: List<DetailData>) {
        moodEntry.details = details
    }

    fun setOccupations(occupations: List<OccupationData>) {
        moodEntry.occupations = occupations
    }

    fun save(applicationContext: Context) {
        AsyncTask.execute {
            MoodLogDb.getInstance(applicationContext).moodEntryDao().insertMoodAllData(moodEntry)
        }
    }
}