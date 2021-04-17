package com.luiz.mobile.poctonegenerator

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var toneDurBar: SeekBar? = null
    var toneDurText: TextView? = null
    private var toneList: ListView? = null
    private var toneListAdapter: ToneAdapter? = null
    private var toneGenerator: ToneGenerator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toneGenerator = ToneGenerator(AudioManager.STREAM_SYSTEM, 100)
        toneDurBar = findViewById(R.id.tonedur)
        toneDurText = findViewById(R.id.tonedurtext)
        toneDurText!!.text = getString(R.string.txt_value_ms, this.toneDurBar!!.progress.toString())

        toneDurBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                toneDurText!!.text = getString(R.string.txt_value_ms, progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        toneList = findViewById(R.id.tonelist)
        toneListAdapter = ToneAdapter(this, R.layout.row, Tone.tones)
        toneList!!.adapter = toneListAdapter
        toneList!!.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val t = parent.getItemAtPosition(position) as Tone
                val type = t.toneType
                val durationMs = toneDurBar!!.progress
                toneGenerator!!.startTone(type, durationMs)
            }
    }
}
