package com.luiz.mobile.poctonegenerator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ToneAdapter(context: Context?, resource: Int, var toneArray: Array<Tone>) :
    ArrayAdapter<Tone?>(context!!, resource, toneArray) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row: View? = convertView
        if (row == null) {
            row = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        }
        val listName = row?.findViewById<TextView>(R.id.name)
        listName?.text = (String.format("%02d", toneArray[position].toneType) + " : " + toneArray[position].toneName)
        val listDesc = row?.findViewById<TextView>(R.id.desc)
        listDesc?.text = toneArray[position].toneDesc
        return row as View
    }
}