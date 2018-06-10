package fr.pasithee.moodlog.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import fr.pasithee.moodlog.R
import fr.pasithee.moodlog.db.entities.OccupationData
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
        saveEntryButton.setOnClickListener {
            val occupations = ArrayList<OccupationData>()
            goThroughView(occupationTableLayout, occupations)
            (activity as LogActivity).logOccupations(occupations)
            (activity as LogActivity).saveEntry()
        }
    }

    private fun goThroughView(view: ViewGroup, occupations: ArrayList<OccupationData>) {
        for (i in 0 until view.childCount) {
            val elem = view.getChildAt(i)
            if (elem is ToggleButton && elem.isChecked) {
                occupations.add(OccupationData(elem.textOn.toString()))
            } else if (elem is ViewGroup) {
                goThroughView(elem, occupations)
            }
        }
    }
}
