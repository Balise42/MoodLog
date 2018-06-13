package fr.pasithee.moodlog.activities

import android.arch.lifecycle.Observer
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.MoodLogDb
import fr.pasithee.moodlog.db.entities.DetailData
import fr.pasithee.moodlog.db.entities.OccupationData
import fr.pasithee.moodlog.util.UIUtils
import kotlinx.android.synthetic.main.fragment_occupation_log.*

class OccupationLogFragment : Fragment() {

    companion object {
        fun newInstance() : OccupationLogFragment {
            return OccupationLogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_occupation_log, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MoodLogDb.getInstance(activity!!.applicationContext).MoodEntryDao().getOccupationNames().observe(this, Observer { list: List<String>? ->
            UIUtils.createButtons(list, activity!!.applicationContext, occupationTableLayout)
        })

        saveEntryButton.setOnClickListener {
            val occupations = ArrayList<String>()
            UIUtils.goThroughView(occupationTableLayout, occupations)
            (activity as LogActivity).logOccupations(occupations)
            (activity as LogActivity).saveEntry()
        }

        addOccupationButton.setOnClickListener {
            AsyncTask.execute {
                MoodLogDb.getInstance(activity!!.applicationContext).MoodEntryDao().insertOccupation(OccupationData(-1, newOccupationText.text.toString()))
            }
        }
    }

}
