package fr.pasithee.moodlog.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.util.createLabels
import fr.pasithee.moodlog.util.getPastWeekDates
import kotlinx.android.synthetic.main.fragment_mood_report.*

import java.util.*

class ViewMoodReportFragment : Fragment() {

    var minDate = Date()
    var maxDate = Date()

    companion object {
        fun newInstance() : ViewMoodReportFragment {
            return ViewMoodReportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mood_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val (a, b) = getPastWeekDates()
        minDate = a
        maxDate = b

        MoodLogDb.getInstance(activity!!.applicationContext).moodEntryDao().getMoodCounts(minDate, maxDate).observe(this, Observer { list ->
            list?.let {
                var labels = ArrayList<String>()
                for (moodCount in it) {
                    labels.add(moodCount.detail + ": " + moodCount.count)
                }
                createLabels(labels, activity!!.applicationContext, viewMoodDataLayout)
            }
        })
    }

}
