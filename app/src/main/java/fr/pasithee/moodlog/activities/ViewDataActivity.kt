package fr.pasithee.moodlog.activities

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.entities.MoodEntryData
import kotlinx.android.synthetic.main.activity_view_data.*
import java.text.SimpleDateFormat
import java.util.*

class ViewDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        displayGraph()
    }

    private fun displayGraph() {
        val dateFormat = SimpleDateFormat("MMM dd HH:mm")
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this, dateFormat)
        graph.gridLabelRenderer.setHumanRounding(false, true)
        graph.viewport.setMinY(1.0)
        graph.viewport.setMaxY(10.0)


        val cal = Calendar.getInstance()
        cal.set(2018, 6, 10, 0, 0)
        val minDate = cal.timeInMillis.toDouble()
        graph.viewport.setMinX(minDate)
        cal.set(2018, 6, 13, 0, 0)
        val maxDate = cal.timeInMillis.toDouble()
        graph.viewport.setMaxX(maxDate)
        graph.viewport.setMinimalViewport(minDate, maxDate, 1.0, 10.0)
        graph.gridLabelRenderer.numHorizontalLabels = 13
        graph.gridLabelRenderer.numVerticalLabels = 10
        graph.gridLabelRenderer.setHorizontalLabelsAngle(90)
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.isYAxisBoundsManual = true

        MoodLogDb.getInstance(applicationContext).MoodEntryDao().getMoodEntryData().observe(this, Observer {list ->
            list?.let {
                graph.removeAllSeries()
                val series = LineGraphSeries<DataPoint>(convertDataToSeries(list))
                series.isDrawDataPoints = true
                graph.addSeries(series)
                graph.gridLabelRenderer.reloadStyles()

            }
        })
    }

    private fun convertDataToSeries(list: List<MoodEntryData>): Array<DataPoint>? {
        return (list.map({it -> DataPoint(it.date, it.level.toDouble())})).toTypedArray()
    }
}
