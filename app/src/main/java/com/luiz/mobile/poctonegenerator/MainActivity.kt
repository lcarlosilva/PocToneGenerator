package com.luiz.mobile.poctonegenerator

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
        class Tone internal constructor(var toneType: Int, var toneName: String, var toneDesc: String) {
                override fun toString(): String {
                        return toneName
                }
        }

        inner class ToneArrayAdapter(context: Context?, resource: Int, var toneArray: Array<Tone>) :
                ArrayAdapter<Tone?>(context!!, resource, toneArray) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                        var row = convertView
                        if (row == null) {
                                val inflater: LayoutInflater = layoutInflater
                                row = inflater.inflate(R.layout.row, parent, false)
                        }
                        val listName = row!!.findViewById<View>(R.id.name) as TextView
                        listName.text = (String.format("%02d", toneArray[position].toneType)
                                + " : " + toneArray[position].toneName)
                        val listDesc = row.findViewById<View>(R.id.desc) as TextView
                        listDesc.text = toneArray[position].toneDesc
                        return row
                }
        }

        private var tones = arrayOf<Tone>(
                Tone(
                        ToneGenerator.TONE_CDMA_ABBR_ALERT,
                        "TONE_CDMA_ABBR_ALERT",
                        "CDMA_ABBR_ALERT tone: 1150Hz+770Hz 400ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ABBR_INTERCEPT,
                        "TONE_CDMA_ABBR_INTERCEPT",
                        "CDMA Abbr Intercept tone: 440Hz 250ms ON, 620Hz 250ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ABBR_REORDER,
                        "TONE_CDMA_ABBR_REORDER",
                        "CDMA Abbr Reorder tone: 480Hz+620Hz 250ms ON, 250ms OFF repeated for 8 times"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ALERT_AUTOREDIAL_LITE,
                        "NE_CDMA_ALERT_AUTOREDIAL_LITE",
                        "CDMA Alert Auto Redial tone: {1245Hz 62ms ON, 659Hz 62ms ON} 3 times, 1245 62ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,
                        "TONE_CDMA_ALERT_CALL_GUARD",
                        "CDMA ALERT CALL GUARD tone: {1319Hz 125ms ON, 125ms OFF} 3 times"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ALERT_INCALL_LITE,
                        "TONE_CDMA_ALERT_INCALL_LITE",
                        "CDMA ALERT INCALL LITE tone: 587Hz 62ms, 784 62ms, 831Hz 62ms, 784Hz 62ms, 1109 62ms, 784Hz 62ms, 831Hz 62ms, 784Hz 62ms"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE,
                        "TONE_CDMA_ALERT_NETWORK_LITE",
                        "CDMA Alert Network Lite tone: 1109Hz 62ms ON, 784Hz 62ms ON, 740Hz 62ms ON 622Hz 62ms ON, 1109Hz 62ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ANSWER,
                        "TONE_CDMA_ANSWER",
                        "CDMA answer tone: silent tone - defintion Frequency 0, 0ms ON, 0ms OFF"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALLDROP_LITE,
                        "TONE_CDMA_CALLDROP_LITE",
                        "CDMA CALLDROP LITE tone: 1480Hz 125ms, 1397Hz 125ms, 784Hz 125ms"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP",
                        "ISDN Call Signal Intergroup tone: {2091Hz 32ms ON, 2556 64ms ON} 8 times, 2091Hz 32ms ON, 400ms OFF, {2091Hz 32ms ON, 2556Hz 64ms ON} times, 2091Hz 32ms ON, 4s OFF."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL",
                        "ISDN Call Signal Normal tone: {2091Hz 32ms ON, 2556 64ms ON} 20 times, 2091 32ms ON, 2556 48ms ON, 4s OFF"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT3,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_PAT3",
                        "ISDN Call sign PAT3 tone: silent tone"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT5,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_PAT5",
                        "ISDN Pat5 tone: silent tone"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT6,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_PAT6",
                        "ISDN Pat6 tone: silent tone"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT7,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_PAT7",
                        "ISDN Pat7 tone: silent tone"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PING_RING,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_PING_RING",
                        "ISDN Ping Ring tone: {2091Hz 32ms ON, 2556Hz 64ms ON} 5 times 2091Hz 20ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI,
                        "TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI",
                        "ISDN Call Signal SP PRI tone:{2091Hz 32ms ON, 2556 64ms ON} 4 times 2091Hz 16ms ON, 200ms OFF, {2091Hz 32ms ON, 2556Hz 64ms ON} 4 times, 2091Hz 16ms ON, 200ms OFF"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_CONFIRM,
                        "TONE_CDMA_CONFIRM",
                        "CDMA Confirm tone: 350Hz+440Hz 100ms ON, 100ms OFF repeated for 3 times"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_DIAL_TONE_LITE,
                        "TONE_CDMA_DIAL_TONE_LITE",
                        "CDMA Dial tone : 425Hz continuous"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK,
                        "TONE_CDMA_EMERGENCY_RINGBACK",
                        "CDMA EMERGENCY RINGBACK tone: {941Hz 125ms ON, 10ms OFF} 3times 4990ms OFF, REPEAT..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_L,
                        "TONE_CDMA_HIGH_L",
                        "TONE_CDMA_HIGH_L tone: {3700Hz 25ms, 4000Hz 25ms} 40 times 4000ms OFF, Repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_PBX_L,
                        "TONE_CDMA_HIGH_PBX_L",
                        "CDMA HIGH PBX L: {3700Hz 25ms, 4000Hz 25ms}20 times, 2000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_PBX_SLS,
                        "TONE_CDMA_HIGH_PBX_SLS",
                        "CDMA HIGH PBX SSL tone:{3700Hz 25ms, 4000Hz 25ms} 8 times 200ms OFF, {3700Hz 25ms, 4000Hz 25ms} 16 times, 200ms OFF, {3700Hz 25ms, 4000Hz 25ms} 8 times, 1000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_PBX_SS,
                        "TONE_CDMA_HIGH_PBX_SS",
                        "CDMA HIGH PBX SS tone: {3700Hz 25ms, 4000Hz 25ms} 8 times 200 ms OFF, {3700Hz 25ms 4000Hz 25ms}8 times, 2000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_PBX_SSL,
                        "TONE_CDMA_HIGH_PBX_SSL",
                        "CDMA HIGH PBX SSL tone:{3700Hz 25ms, 4000Hz 25ms} 8 times 200ms OFF, {3700Hz 25ms, 4000Hz 25ms} 8 times, 200ms OFF, {3700Hz 25ms, 4000Hz 25ms} 16 times, 1000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_PBX_S_X4,
                        "TONE_CDMA_HIGH_PBX_S_X4",
                        "CDMA HIGH PBX X S4 tone: {3700Hz 25ms 4000Hz 25ms} 8 times, 200ms OFF, {3700Hz 25ms 4000Hz 25ms} 8 times, 200ms OFF, {3700Hz 25ms 4000Hz 25ms} 8 times, 200ms OFF, {3700Hz 25ms 4000Hz 25ms} 8 times, 800ms OFF, REPEAT..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_SLS,
                        "TONE_CDMA_HIGH_SLS",
                        "CDMA HIGH SLS tone: {3700Hz 25ms, 4000Hz 25ms} 10 times, 500ms OFF, {3700Hz 25ms, 4000Hz 25ms} 20 times, 500ms OFF, {3700Hz 25ms, 4000Hz 25ms} 10 times, 3000ms OFF, REPEAT"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_SS,
                        "TONE_CDMA_HIGH_SS",
                        "CDMA HIGH SS tone: {3700Hz 25ms, 4000Hz 25ms} repeat 16 times, 400ms OFF, repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_SSL,
                        "TONE_CDMA_HIGH_SSL",
                        "CDMA HIGH SSL tone: {3700Hz 25ms, 4000Hz 25ms} 8 times, 200ms OFF, {3700Hz 25ms, 4000Hz 25ms} repeat 8 times, 200ms OFF, {3700Hz 25ms, 4000Hz 25ms} repeat 16 times, 4000ms OFF, repeat ..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_SS_2,
                        "TONE_CDMA_HIGH_SS_2",
                        "CDMA HIGH SS2 tone: {3700Hz 25ms, 4000Hz 25ms} 20 times, 1000ms OFF, {3700Hz 25ms, 4000Hz 25ms} 20 times, 3000ms OFF, repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_HIGH_S_X4,
                        "TONE_CDMA_HIGH_S_X4",
                        "CDMA HIGH S X4 tone: {3700Hz 25ms, 4000Hz 25ms} 10 times, 500ms OFF, {3700Hz 25ms, 4000Hz 25ms} 10 times, 500ms OFF, {3700Hz 25ms, 4000Hz 25ms} 10 times, 500ms OFF, {3700Hz 25ms, 4000Hz 25ms} 10 times, 2500ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_INTERCEPT,
                        "TONE_CDMA_INTERCEPT",
                        "CDMA Intercept tone: 440Hz 250ms ON, 620Hz 250ms ON ..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE,
                        "TONE_CDMA_KEYPAD_VOLUME_KEY_LITE",
                        "CDMA KEYPAD Volume key lite tone: 941Hz+1477Hz 120ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_L,
                        "TONE_CDMA_LOW_L",
                        "TONE_CDMA_LOW_L tone: {1300Hz 25ms, 1450Hz 25ms} 40 times, 4000ms OFF, Repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_PBX_L,
                        "TONE_CDMA_LOW_PBX_L",
                        "CDMA LOW PBX L: {1300Hz 25ms,1450Hz 25ms}20 times, 2000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_PBX_SLS,
                        "TONE_CDMA_LOW_PBX_SLS",
                        "CDMA HIGH PBX SLS tone:{1300Hz 25ms, 1450Hz 25ms} 8 times 200ms OFF, {1300Hz 25ms, 1450Hz 25ms} 16 times, 200ms OFF, {1300Hz 25ms, 1450Hz 25ms} 8 times, 1000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_PBX_SS,
                        "TONE_CDMA_LOW_PBX_SS",
                        "CDMA LOW PBX SS tone: {1300Hz 25ms, 1450Hz 25ms} 8 times 200 ms OFF, {1300Hz 25ms 1450Hz 25ms}8 times, 2000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_PBX_SSL,
                        "TONE_CDMA_LOW_PBX_SSL",
                        "CDMA LOW PBX SSL tone:{1300Hz 25ms, 1450Hz 25ms} 8 times 200ms OFF, {1300Hz 25ms, 1450Hz 25ms} 8 times, 200ms OFF, {1300Hz 25ms, 1450Hz 25ms} 16 times, 1000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_PBX_S_X4,
                        "TONE_CDMA_LOW_PBX_S_X4",
                        "CDMA LOW PBX X S4 tone: {1300Hz 25ms 1450Hz 25ms} 8 times, 200ms OFF, {1300Hz 25ms 1450Hz 25ms} 8 times, 200ms OFF, {1300Hz 25ms 1450Hz 25ms} 8 times, 200ms OFF, {1300Hz 25ms 1450Hz 25ms} 8 times, 800ms OFF, REPEAT..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_SLS,
                        "TONE_CDMA_LOW_SLS",
                        "CDMA LOW SLS tone: {1300Hz 25ms, 1450Hz 25ms} 10 times, 500ms OFF, {1300Hz 25ms, 1450Hz 25ms} 20 times, 500ms OFF, {1300Hz 25ms, 1450Hz 25ms} 10 times, 3000ms OFF, REPEAT"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_SS,
                        "TONE_CDMA_LOW_SS",
                        "CDMA LOW SS tone: {1300z 25ms, 1450Hz 25ms} repeat 16 times, 400ms OFF, repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_SSL,
                        "TONE_CDMA_LOW_SSL",
                        "CDMA LOW SSL tone: {1300Hz 25ms, 1450Hz 25ms} 8 times, 200ms OFF, {1300Hz 25ms, 1450Hz 25ms} repeat 8 times, 200ms OFF, {1300Hz 25ms, 1450Hz 25ms} repeat 16 times, 4000ms OFF, repeat ..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_SS_2,
                        "TONE_CDMA_LOW_SS_2",
                        "CDMA LOW SS2 tone: {1300Hz 25ms, 1450Hz 25ms} 20 times, 1000ms OFF, {1300Hz 25ms, 1450Hz 25ms} 20 times, 3000ms OFF, repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_LOW_S_X4,
                        "TONE_CDMA_LOW_S_X4",
                        "CDMA LOW S X4 tone: {2600Hz 25ms, 2900Hz 25ms} 10 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 10 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 10 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 10 times, 2500ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_L,
                        "TONE_CDMA_MED_L",
                        "TONE_CDMA_MED_L tone: {2600Hz 25ms, 2900Hz 25ms} 40 times 4000ms OFF, Repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_PBX_L,
                        "TONE_CDMA_MED_PBX_L",
                        "CDMA MED PBX L: {2600Hz 25ms, 2900Hz 25ms}20 times, 2000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_PBX_SLS,
                        "TONE_CDMA_MED_PBX_SLS",
                        "CDMA HIGH PBX SLS tone:{2600Hz 25ms, 2900Hz 25ms} 8 times 200ms OFF, {2600Hz 25ms, 2900Hz 25ms} 16 times, 200ms OFF, {2600Hz 25ms, 2900Hz 25ms} 8 times, 1000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_PBX_SS,
                        "TONE_CDMA_MED_PBX_SS",
                        "CDMA MED PBX SS tone: {2600Hz 25ms, 2900Hz 25ms} 8 times 200 ms OFF, {2600Hz 25ms 2900Hz 25ms}8 times, 2000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_PBX_SSL,
                        "TONE_CDMA_MED_PBX_SSL",
                        "CDMA MED PBX SSL tone:{2600Hz 25ms, 2900Hz 25ms} 8 times 200ms OFF, {2600Hz 25ms, 2900Hz 25ms} 8 times, 200ms OFF, {2600Hz 25ms, 2900Hz 25ms} 16 times, 1000ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_PBX_S_X4,
                        "TONE_CDMA_MED_PBX_S_X4",
                        "CDMA MED PBX X S4 tone: {2600Hz 25ms 2900Hz 25ms} 8 times, 200ms OFF, {2600Hz 25ms 2900Hz 25ms} 8 times, 200ms OFF, {2600Hz 25ms 2900Hz 25ms} 8 times, 200ms OFF, {2600Hz 25ms 2900Hz 25ms} 8 times, 800ms OFF, REPEAT..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_SLS,
                        "TONE_CDMA_MED_SLS",
                        "CDMA MED SLS tone: {2600Hz 25ms, 2900Hz 25ms} 10 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 20 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 10 times, 3000ms OFF, REPEAT"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_SS,
                        "TONE_CDMA_MED_SS",
                        "CDMA MED SS tone: {2600Hz 25ms, 2900Hz 25ms} repeat 16 times, 400ms OFF, repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_SSL,
                        "TONE_CDMA_MED_SSL",
                        "CDMA MED SSL tone: {2600Hz 25ms, 2900Hz 25ms} 8 times, 200ms OFF, {2600Hz 25ms, 2900Hz 25ms} repeat 8 times, 200ms OFF, {2600Hz 25ms, 2900Hz 25ms} repeat 16 times, 4000ms OFF, repeat ..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_SS_2,
                        "TONE_CDMA_MED_SS_2",
                        "CDMA MED SS2 tone: {2600Hz 25ms, 2900Hz 25ms} 20 times, 1000ms OFF, {2600Hz 25ms, 2900Hz 25ms} 20 times, 3000ms OFF, repeat ...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_MED_S_X4,
                        "TONE_CDMA_MED_S_X4",
                        "CDMA MED S X4 tone: {2600Hz 25ms, 2900Hz 25ms} 10 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 10 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 10 times, 500ms OFF, {2600Hz 25ms, 2900Hz 25ms} 10 times, 2500ms OFF, REPEAT...."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_NETWORK_BUSY,
                        "TONE_CDMA_NETWORK_BUSY",
                        "CDMA Network Busy tone: 480Hz+620Hz 500ms ON, 500ms OFF continuous"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_NETWORK_BUSY_ONE_SHOT,
                        "TONE_CDMA_NETWORK_BUSY_ONE_SHOT",
                        "CDMA_NETWORK_BUSY_ONE_SHOT tone: 425Hz 500ms ON, 500ms OFF."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_NETWORK_CALLWAITING,
                        "TONE_CDMA_NETWORK_CALLWAITING",
                        "CDMA Network Callwaiting tone: 440Hz 300ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_NETWORK_USA_RINGBACK,
                        "TONE_CDMA_NETWORK_USA_RINGBACK",
                        "CDMA USA Ringback: 440Hz+480Hz 2s ON, 4000 OFF ..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_ONE_MIN_BEEP,
                        "TONE_CDMA_ONE_MIN_BEEP",
                        "CDMA One Min Beep tone: 1150Hz+770Hz 400ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_PIP,
                        "TONE_CDMA_PIP",
                        "CDMA PIP tone: 480Hz 100ms ON, 100ms OFF repeated for 4 times"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_PRESSHOLDKEY_LITE,
                        "TONE_CDMA_PRESSHOLDKEY_LITE",
                        "CDMA PRESSHOLDKEY LITE tone: 587Hz 375ms ON, 1175Hz 125ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_REORDER,
                        "TONE_CDMA_REORDER",
                        "CDMA Reorder tone: 480Hz+620Hz 250ms ON, 250ms OFF..."
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_SIGNAL_OFF,
                        "TONE_CDMA_SIGNAL_OFF",
                        "CDMA_SIGNAL_OFF - silent tone"
                ),
                Tone(
                        ToneGenerator.TONE_CDMA_SOFT_ERROR_LITE,
                        "TONE_CDMA_SOFT_ERROR_LITE",
                        "CDMA SOFT ERROR LITE tone: 1047Hz 125ms ON, 370Hz 125ms"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_0,
                        "TONE_DTMF_0",
                        "DTMF tone for key 0: 1336Hz, 941Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_1,
                        "TONE_DTMF_1",
                        "DTMF tone for key 1: 1209Hz, 697Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_2,
                        "TONE_DTMF_2",
                        "DTMF tone for key 2: 1336Hz, 697Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_3,
                        "TONE_DTMF_3",
                        "DTMF tone for key 3: 1477Hz, 697Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_4,
                        "TONE_DTMF_4",
                        "DTMF tone for key 4: 1209Hz, 770Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_5,
                        "TONE_DTMF_5",
                        "DTMF tone for key 5: 1336Hz, 770Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_6,
                        "TONE_DTMF_6",
                        "DTMF tone for key 6: 1477Hz, 770Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_7,
                        "TONE_DTMF_7",
                        "DTMF tone for key 7: 1209Hz, 852Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_8,
                        "TONE_DTMF_8",
                        "DTMF tone for key 8: 1336Hz, 852Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_9,
                        "TONE_DTMF_9",
                        "DTMF tone for key 9: 1477Hz, 852Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_A,
                        "TONE_DTMF_A",
                        "DTMF tone for key A: 1633Hz, 697Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_B,
                        "TONE_DTMF_B",
                        "DTMF tone for key B: 1633Hz, 770Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_C,
                        "TONE_DTMF_C",
                        "DTMF tone for key C: 1633Hz, 852Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_D,
                        "TONE_DTMF_D",
                        "DTMF tone for key D: 1633Hz, 941Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_P,
                        "TONE_DTMF_P",
                        "DTMF tone for key #: 1477Hz, 941Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_DTMF_S,
                        "TONE_DTMF_S",
                        "DTMF tone for key *: 1209Hz, 941Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_PROP_ACK,
                        "TONE_PROP_ACK",
                        "Proprietary tone, positive acknowlegement: 1200Hz, 100ms ON, 100ms OFF 2 bursts"
                ),
                Tone(
                        ToneGenerator.TONE_PROP_BEEP,
                        "TONE_PROP_BEEP",
                        "Proprietary tone, general beep: 400Hz+1200Hz, 35ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_PROP_BEEP2,
                        "TONE_PROP_BEEP2",
                        "Proprietary tone, general double beep: twice 400Hz+1200Hz, 35ms ON, 200ms OFF, 35ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_PROP_NACK,
                        "TONE_PROP_NACK",
                        "Proprietary tone, negative acknowlegement: 300Hz+400Hz+500Hz, 400ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_PROP_PROMPT,
                        "TONE_PROP_PROMPT",
                        "Proprietary tone, prompt tone: 400Hz+1200Hz, 200ms ON"
                ),
                Tone(
                        ToneGenerator.TONE_SUP_BUSY,
                        "TONE_SUP_BUSY",
                        "Call supervisory tone, Busy: CEPT: 425Hz, 500ms ON, 500ms OFF..."
                ),
                Tone(
                        ToneGenerator.TONE_SUP_CALL_WAITING,
                        "TONE_SUP_CALL_WAITING",
                        "Call supervisory tone, Call Waiting: CEPT, JAPAN: 425Hz, 200ms ON, 600ms OFF, 200ms ON, 3s OFF..."
                ),
                Tone(
                        ToneGenerator.TONE_SUP_CONFIRM,
                        "TONE_SUP_CONFIRM",
                        "Call supervisory tone (IS-95), confirm tone: a 350 Hz tone added to a 440 Hz tone repeated 3 times in a 100 ms on, 100 ms off cycle"
                ),
                Tone(
                        ToneGenerator.TONE_SUP_CONGESTION,
                        "TONE_SUP_CONGESTION",
                        "Call supervisory tone, Congestion: CEPT, JAPAN: 425Hz, 200ms ON, 200ms OFF..."
                ),
                Tone(
                        ToneGenerator.TONE_SUP_CONGESTION_ABBREV,
                        "TONE_SUP_CONGESTION_ABBREV",
                        "Call supervisory tone (IS-95), abbreviated congestion: congestion tone limited to 4 seconds"
                ),
                Tone(
                        ToneGenerator.TONE_SUP_DIAL,
                        "TONE_SUP_DIAL",
                        "Call supervisory tone, Dial tone: CEPT: 425Hz, continuous ANSI (IS-95): 350Hz+440Hz, continuous JAPAN: 400Hz, continuous"
                ),
                Tone(
                        ToneGenerator.TONE_SUP_ERROR,
                        "TONE_SUP_ERROR",
                        "Call supervisory tone, Error/Special info: 950Hz+1400Hz+1800Hz, 330ms ON, 1s OFF..."
                ),
                Tone(
                        ToneGenerator.TONE_SUP_INTERCEPT,
                        "TONE_SUP_INTERCEPT",
                        "Call supervisory tone (IS-95), intercept tone: alternating 440 Hz and 620 Hz tones, each on for 250 ms"
                ),
                Tone(
                        ToneGenerator.TONE_SUP_INTERCEPT_ABBREV,
                        "TONE_SUP_INTERCEPT_ABBREV",
                        "Call supervisory tone (IS-95), abbreviated intercept: intercept tone limited to 4 seconds"
                ),
                Tone(
                        ToneGenerator.TONE_SUP_PIP,
                        "TONE_SUP_PIP",
                        "Call supervisory tone (IS-95), pip tone: four bursts of 480 Hz tone (0.1 s on, 0.1 s off)."
                ),
                Tone(
                        ToneGenerator.TONE_SUP_RADIO_ACK,
                        "TONE_SUP_RADIO_ACK",
                        "Call supervisory tone, Radio path acknowlegment : CEPT, ANSI: 425Hz, 200ms ON JAPAN: 400Hz, 1s ON, 2s OFF..."
                ),
                Tone(
                        ToneGenerator.TONE_SUP_RADIO_NOTAVAIL,
                        "TONE_SUP_RADIO_NOTAVAIL",
                        "Call supervisory tone, Radio path not available: 425Hz, 200ms ON, 200 OFF 3 bursts"
                ),
                Tone(
                        ToneGenerator.TONE_SUP_RINGTONE,
                        "TONE_SUP_RINGTONE",
                        "Call supervisory tone, Ring Tone: CEPT, JAPAN: 425Hz, 1s ON, 4s OFF..."
                )
        )
        private var toneDurBar: SeekBar? = null
        var toneDurText: TextView? = null
        private var toneList: ListView? = null
        private var toneListAdapter: ToneArrayAdapter? = null
        private var toneGenerator: ToneGenerator? = null

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                toneDurBar = findViewById(R.id.tonedur)
                toneDurText = findViewById(R.id.tonedurtext)
                toneDurText!!.text = this.toneDurBar!!.progress.toString() + " ms"
                toneDurBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                                toneDurText!!.text = "$progress ms"
                        }

                        override fun onStartTrackingTouch(seekBar: SeekBar) {}
                        override fun onStopTrackingTouch(seekBar: SeekBar) {}
                })
                toneList = findViewById(R.id.tonelist)
                toneListAdapter = ToneArrayAdapter(this,
                        R.layout.row, tones
                )
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
