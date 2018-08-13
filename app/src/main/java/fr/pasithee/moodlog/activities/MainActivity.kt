package fr.pasithee.moodlog.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.OccupationData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().add(R.id.layout_main_menu, MainMenuFragment.newInstance()).addToBackStack(null).commit()
        setContentView(R.layout.fragment_main_menu)
    }

    fun logNew(view : View) {
        val model = ViewModelProviders.of(this).get(MoodEntryModel::class.java)
        model.createMoodEntry()
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, LevelLogFragment.newInstance()).addToBackStack(null).commit()
        setContentView(R.layout.activity_log)
    }

    fun logLevel(level : Int) {
        val model = ViewModelProviders.of(this).get(MoodEntryModel::class.java)
        model.setLevel(level)
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, DetailLogFragment.newInstance()).addToBackStack(null).commit()
    }

    fun logDetails(details : List<String>) {
        val model = ViewModelProviders.of(this).get(MoodEntryModel::class.java)
        model.setDetails(details.map {it -> DetailData(it) })
        supportFragmentManager.beginTransaction().replace(R.id.log_layout, OccupationLogFragment.newInstance()).addToBackStack(null).commit()
    }

    fun logOccupations(occupations: List<String>) {
        val model = ViewModelProviders.of(this).get(MoodEntryModel::class.java)
        model.setOccupations(occupations.map { it-> OccupationData(it) })
    }

    fun saveEntry() {
        val model = ViewModelProviders.of(this).get(MoodEntryModel::class.java)
        model.save(applicationContext)
        supportFragmentManager.beginTransaction().replace(R.id.layout_main_menu, MainMenuFragment.newInstance()).addToBackStack(null).commit()
        setContentView(R.layout.fragment_main_menu)
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
