package fr.pasithee.moodlog.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.pasithee.moodlog.R

class MainMenuFragment : Fragment() {
    companion object {
        fun newInstance() : MainMenuFragment {
            return MainMenuFragment()
        }
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

}