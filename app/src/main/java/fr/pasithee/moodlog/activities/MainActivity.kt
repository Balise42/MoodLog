package fr.pasithee.moodlog.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.pasithee.moodlog.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun logNew(view : View) {
        val logIntent = Intent(this, LogActivity::class.java)
        startActivity(logIntent)
    }


    fun settings(view : View) {
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    fun viewData(view : View) {
        val viewDataIntent = Intent(this, ViewDataActivity::class.java)
        startActivity(viewDataIntent)
    }

    fun viewOccupationReport(view : View) {
        supportFragmentManager.beginTransaction().add(R.id.mood_report, ViewMoodReportFragment.newInstance()).addToBackStack(null).commit()
        setContentView(R.layout.fragment_mood_report)
    }
}
