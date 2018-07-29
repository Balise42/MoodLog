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
import fr.pasithee.moodlog.util.UIUtils
import kotlinx.android.synthetic.main.fragment_detail_log.*

class DetailLogFragment : Fragment() {

    companion object {
        fun newInstance(): DetailLogFragment {
            return DetailLogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MoodLogDb.getInstance(activity!!.applicationContext).moodEntryDao().getDetailNames().observe(this, Observer { list: List<String>? ->
            UIUtils.createButtons(list, activity!!.applicationContext, detailTableLayout)
        })

        logActivityButton.setOnClickListener {
            val details = ArrayList<String>()
            UIUtils.goThroughView(detailTableLayout, details)
            (activity as LogActivity).logDetails(details)
        }

        addDetailButton.setOnClickListener {
            AsyncTask.execute {
                MoodLogDb.getInstance(activity!!.applicationContext).moodEntryDao().insertDetail(DetailData(-1, newDetailText.text.toString()))
            }
        }
    }

}
