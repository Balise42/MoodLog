package fr.pasithee.moodlog.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import fr.pasithee.moodlog.R
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
        logActivityButton.setOnClickListener {
            val details = ArrayList<String>()
            goThroughView(tableLayout, details)
            (activity as LogActivity).logDetails(details)
        }
    }

    private fun goThroughView(view: ViewGroup, details: ArrayList<String>) {
        for (i in 0 until view.childCount) {
            val elem = view.getChildAt(i)
            if (elem is ToggleButton && elem.isChecked) {
                details.add(elem.textOn.toString())
            } else if (elem is ViewGroup) {
                goThroughView(elem, details)
            }
        }
    }

}
