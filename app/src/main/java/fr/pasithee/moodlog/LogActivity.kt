package fr.pasithee.moodlog

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
    }

    fun moodDetails(view : View) {
        val detailIntent = Intent(this, DetailLogActivity::class.java)
        startActivity(detailIntent)
    }
}
