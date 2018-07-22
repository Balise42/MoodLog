package fr.pasithee.moodlog.activities

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.entities.MoodEntryData
import java.util.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun reinitDatabase(view : View) {
        AsyncTask.execute({
            MoodLogDb.getInstance(applicationContext).MoodEntryDao().reinitDb()
        })
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun initDbForTests(view : View) {
        AsyncTask.execute({
            val dao = MoodLogDb.getInstance(applicationContext).MoodEntryDao()
            dao.reinitDb()
            val cal = Calendar.getInstance()
            cal.set(2018, 6, 20, 9, 30)
            dao.insertMood(MoodEntryData(0, cal.time, 5))
            cal.set(2018, 6, 20, 13, 0)
            dao.insertMood(MoodEntryData(0, cal.time, 3))
            cal.set(2018, 6, 20, 19, 0)
            dao.insertMood(MoodEntryData(0, cal.time, 8))
            cal.set(2018, 6, 20, 23, 30)
            dao.insertMood(MoodEntryData(0, cal.time, 3))
            cal.set(2018, 6, 21, 9, 0)
            dao.insertMood(MoodEntryData(0, cal.time, 8))
            cal.set(2018, 6, 21, 20, 0)
            dao.insertMood(MoodEntryData(0, cal.time, 2))
            cal.set(2018, 6, 22, 7, 0)
            dao.insertMood(MoodEntryData(0, cal.time, 4))
            cal.set(2018, 6, 22, 11, 21)
            dao.insertMood(MoodEntryData(0, cal.time, 5))
            cal.set(2018, 6, 22, 14, 15)
            dao.insertMood(MoodEntryData(0, cal.time, 6))
            cal.set(2018, 6, 22, 19, 0)
            dao.insertMood(MoodEntryData(0, cal.time, 5))
        })
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
