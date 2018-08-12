package fr.pasithee.moodlog.util

import android.content.Context
import android.view.ViewGroup
import android.widget.*

fun createButtons(list: List<String>?, context: Context, view: TableLayout) {
    view.removeAllViews()
    var row = TableRow(context)
    view.addView(row)
    for (i in 0 until list!!.size) {
        if (i % 3 == 0 && i != 0) {
            row = TableRow(context)
            view.addView(row)
        }
        val button = ToggleButton(context)
        button.text = list[i]
        button.textOn = list[i]
        button.textOff = list[i]
        row.addView(button)
    }
}

fun createLabels(list: List<String>?, context: Context, view: LinearLayout) {
    view.removeAllViews()
    for (i in 0 until list!!.size) {
        val label = TextView(context)
        label.text = list[i]
        view.addView(label)
    }
}

fun goThroughView(view: ViewGroup, details: ArrayList<String>) {
    for (i in 0 until view.childCount) {
        val elem = view.getChildAt(i)
        if (elem is ToggleButton && elem.isChecked) {
            details.add(elem.textOn.toString())
        } else if (elem is ViewGroup) {
            goThroughView(elem, details)
        }
    }
}