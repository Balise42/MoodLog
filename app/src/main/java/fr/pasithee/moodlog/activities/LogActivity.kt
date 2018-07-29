package fr.pasithee.moodlog.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import fr.pasithee.moodlog.db.entities.OccupationData
import java.util.*

class LogActivity : AppCompatActivity() {

    private var moodEntry = MoodEntryData(0, Date(), -1, emptyList(), emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(R.id.log_layout, LevelLogFragment.newInstance()).addToBackStack(null).commit()
        setContentView(R.layout.activity_log)
    }

    fun logLevel(level : Int) {
        moodEntry.level = level
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, DetailLogFragment.newInstance()).addToBackStack(null).commit()
    }

    fun logDetails(details : List<String>) {
        moodEntry.details = details.map {it -> DetailData(it) }
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, OccupationLogFragment.newInstance()).addToBackStack(null).commit()
    }

    fun logOccupations(occupations: List<String>) {
        moodEntry.occupations = occupations.map { it->OccupationData(it) }
    }

    fun saveEntry() {
        AsyncTask.execute {
            MoodLogDb.getInstance(applicationContext).moodEntryDao().insertMoodAllData(moodEntry)
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
