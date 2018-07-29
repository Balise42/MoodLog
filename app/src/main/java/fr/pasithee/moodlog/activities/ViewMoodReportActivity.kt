package fr.pasithee.moodlog.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.utils.getPastWeekDates

import java.util.*

class ViewMoodReportActivity : AppCompatActivity() {

    var minDate = Date()
    var maxDate = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val (a, b) = getPastWeekDates()
        minDate = a
        maxDate = b

        MoodLogDb.getInstance(applicationContext).moodEntryDao().getMoodCounts(minDate, maxDate).observe(this, Observer { list ->
            list?.let {
                // TODO do something with that thing :P
               System.out.println(list)
            }
        })
    }

}
