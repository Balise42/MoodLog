package fr.pasithee.moodlog.activities

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.entities.MoodEntryData
import kotlinx.android.synthetic.main.activity_view_data.*

class ViewDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        displayGraph()
    }

    private fun displayGraph() {
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        graph.gridLabelRenderer.setHumanRounding(false)
        MoodLogDb.getInstance(applicationContext).MoodEntryDao().getMoodEntryData().observe(this, Observer {list ->
            list?.let {
                val series = LineGraphSeries<DataPoint>(convertDataToSeries(list))
                graph.removeAllSeries()
                graph.addSeries(series)
            }
        })
    }

    private fun convertDataToSeries(list: List<MoodEntryData>): Array<DataPoint>? {
        return (list.map({it -> DataPoint(it.date, it.level.toDouble())})).toTypedArray()
    }
}
