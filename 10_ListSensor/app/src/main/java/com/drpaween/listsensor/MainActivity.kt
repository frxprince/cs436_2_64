package com.drpaween.listsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener ,SensorEventListener{
lateinit var manager:SensorManager
    lateinit var sensor:Sensor
    lateinit var allsensor:MutableList<Sensor>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager

         allsensor=manager.getSensorList(Sensor.TYPE_ALL)
var sensor_name= mutableListOf<String>()
 for (name in allsensor) {sensor_name.add(name.name)}
var adapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,sensor_name)
spinner.adapter=adapter
        spinner.onItemSelectedListener=this
        sensor=allsensor[0]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this,sensor)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
if(sensor!=null)manager.unregisterListener(this,sensor)
        sensor=allsensor[position]
        textView.text="""Name:${sensor.name}
            |Vender:${sensor.vendor}
            |Versiom:${sensor.version}
            |Max:${sensor.maximumRange}   Resolution:${sensor.resolution}
        """.trimMargin()
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var msg:String=""
        for ((index,item) in event!!.values.withIndex())
        {msg+="Parameter[$index]= $item\n"
        }
        textView2.text=msg
    }
}
