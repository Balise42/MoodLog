package fr.pasithee.moodlog.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.pasithee.moodlog.R
import kotlinx.android.synthetic.main.fragment_level_log.*

class LevelLogFragment : Fragment() {

    companion object {
        fun newInstance(): LevelLogFragment {
            return LevelLogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_level_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO dégueu.
        mood1.setOnClickListener {
            (activity as MainActivity).logLevel(1)
        }
        mood2.setOnClickListener {
            (activity as MainActivity).logLevel(2)
        }
        mood3.setOnClickListener {
            (activity as MainActivity).logLevel(3)
        }
        mood4.setOnClickListener {
            (activity as MainActivity).logLevel(4)
        }
        mood5.setOnClickListener {
            (activity as MainActivity).logLevel(5)
        }
        mood6.setOnClickListener {
            (activity as MainActivity).logLevel(6)
        }
        mood7.setOnClickListener {
            (activity as MainActivity).logLevel(7)
        }
        mood8.setOnClickListener {
            (activity as MainActivity).logLevel(8)
        }
        mood9.setOnClickListener {
            (activity as MainActivity).logLevel(9)
        }
        mood10.setOnClickListener {
            (activity as MainActivity).logLevel(10)
        }
    }
}
