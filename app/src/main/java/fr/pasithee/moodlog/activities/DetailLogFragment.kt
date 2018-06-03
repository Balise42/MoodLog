package fr.pasithee.moodlog.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            details.add("Happy")
            details.add("Plop")
            (activity as LogActivity).logDetails(details)
        }
    }

}
