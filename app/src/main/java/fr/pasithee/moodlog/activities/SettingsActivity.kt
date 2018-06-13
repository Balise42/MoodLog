package fr.pasithee.moodlog.activities

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.MoodLogDb

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
}
