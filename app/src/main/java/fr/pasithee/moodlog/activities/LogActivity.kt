package fr.pasithee.moodlog.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.model.MoodRecord
import java.util.*

class LogActivity : AppCompatActivity() {
    private var moodRecord : MoodRecord = MoodRecord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(R.id.log_layout, LevelLogFragment.newInstance()).addToBackStack(null).commit()
        setContentView(R.layout.activity_log)
    }

    fun logLevel(level : Int) {
        moodRecord.moodRecord.level = level
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, DetailLogFragment.newInstance()).addToBackStack(null).commit()
    }

    fun logDetails(details : List<String>) {
        moodRecord.details = details
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, OccupationLogFragment.newInstance()).addToBackStack(null).commit()
    }

}
