package fr.pasithee.moodlog.activities

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    private var minDate = 0.0
    private var maxDate = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        initGraph()
    }

    private fun initGraph() {
        val cal = getCalendarForNow()
        cal.add(Calendar.DAY_OF_YEAR, 1)
        maxDate = cal.timeInMillis.toDouble()
        cal.add(Calendar.DAY_OF_YEAR, -7)
        minDate = cal.timeInMillis.toDouble()

        displayGraph()

        MoodLogDb.getInstance(applicationContext).moodEntryDao().getMoodEntryData().observe(this, Observer { list ->
            list?.let {
                graph.removeAllSeries()
                val series = LineGraphSeries<DataPoint>(convertDataToSeries(list))
                series.isDrawDataPoints = true
                graph.addSeries(series)
                graph.gridLabelRenderer.reloadStyles()

            }
        })

    }

    fun updateMinDateFromUI(view : View) {
        val cal = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("US_us"))
        cal.time  = dateFormat.parse(minDateInput.text.toString())
        updateMinDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }

    fun updateMaxDateFromUI(view : View) {
        val cal = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("US_us"))
        cal.time  = dateFormat.parse(maxDateInput.text.toString())
        updateMaxDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
    }

    private fun updateMinDate(year: Int, month: Int, day: Int) {
        minDate = getCalendarForDate(year, month, day).timeInMillis.toDouble()
        displayGraph()
    }

    private fun updateMaxDate(year: Int, month: Int, day: Int) {
        val cal = getCalendarForDate(year, month, day)
        cal.add(Calendar.DAY_OF_MONTH, 1)
        maxDate = cal.timeInMillis.toDouble()
        displayGraph()
    }

    private fun getCalendarForNow() : Calendar {
        return getCalendarForDate(-1,-1,-1)
    }

    private fun getCalendarForDate(year: Int, month: Int, day: Int): Calendar {
        val cal = Calendar.getInstance()
        if (year >= 0 && month >= 0 && day >= 0) {
            cal.set(year, month, day)
        }
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        cal.set(Calendar.AM_PM, Calendar.AM)
        return cal
    }

    private fun displayGraph() {
        val dateFormat = SimpleDateFormat("MMM dd HH:mm", Locale("US_us"))
        graph.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this, dateFormat)
        graph.gridLabelRenderer.setHumanRounding(false, true)
        graph.viewport.setMinY(1.0)
        graph.viewport.setMaxY(10.0)
        graph.viewport.setMinX(minDate)
        graph.viewport.setMaxX(maxDate)
        graph.viewport.setMinimalViewport(minDate, maxDate, 1.0, 10.0)
        graph.gridLabelRenderer.numHorizontalLabels = computeNumTicks()
        graph.gridLabelRenderer.numVerticalLabels = 10
        graph.gridLabelRenderer.setHorizontalLabelsAngle(90)
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.isYAxisBoundsManual = true
        graph.onDataChanged(false, false)
    }

    private fun computeNumTicks(): Int {
        // TODO this is quite ugly and probably not resilient to corner cases (leap second comes to mind), should be reimplemented
        return ((maxDate - minDate)/86400000 * 3 + 1).toInt()
    }

    private fun convertDataToSeries(list: List<MoodEntryData>): Array<DataPoint>? {
        return (list.map { it -> DataPoint(it.date, it.level.toDouble())}).toTypedArray()
    }
}
