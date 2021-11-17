package com.drpaween.sensorreaddata

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),SensorEventListener {
lateinit var sensor1:Sensor
    lateinit var sensor2:Sensor
    lateinit var manager:SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor1=manager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensor2=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        button.setOnClickListener {
            manager.registerListener(this,sensor1,SensorManager.SENSOR_DELAY_NORMAL)
            manager.registerListener(this,sensor2,SensorManager.SENSOR_DELAY_NORMAL)
        }
        button2.setOnClickListener {
            manager.unregisterListener(this,sensor1)
            manager.unregisterListener(this,sensor2)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
       if (event!!.sensor==sensor1)
           textView.text=event.values[0].toString()
        if (event!!.sensor==sensor2)
            textView2.text="X:${event.values[0]}\n Y:${event.values[1]}\n Z:${event.values[2]}"
    }
}
