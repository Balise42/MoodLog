package fr.pasithee.moodlog.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.pasithee.moodlog.R

class DetailLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_log)
    }

    fun logActivity(view : View) {
        val activityLog = Intent(this, ActivityLogActivity::class.java)
        startActivity(activityLog)
    }
}
