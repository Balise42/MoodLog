package fr.pasithee.moodlog.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.MoodEntryData
import java.util.*

class LogActivity : AppCompatActivity() {

    private var moodEntry = MoodEntryData(-1, Date(), -1, emptyList(), emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(R.id.log_layout, LevelLogFragment.newInstance()).addToBackStack(null).commit()
        setContentView(R.layout.activity_log)
    }

    fun logLevel(level : Int) {
        moodEntry.level = level
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, DetailLogFragment.newInstance()).addToBackStack(null).commit()
    }

    fun logDetails(details : List<DetailData>) {
        moodEntry.details = details
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, OccupationLogFragment.newInstance()).addToBackStack(null).commit()
    }

}
