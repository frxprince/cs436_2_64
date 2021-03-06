package com.drpaween.protractor

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.round

class MainActivity : AppCompatActivity(),SensorEventListener {
lateinit var sensor:Sensor
    lateinit var manager:SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        seekBar.max=180
        seekBar.progressDrawable  = ContextCompat.getDrawable(this,R.mipmap.bg)
        seekBar.background=ContextCompat.getDrawable(this,R.mipmap.border)
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this,sensor)
    }

    override fun onResume() {
        super.onResume()
        manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
textView.text="X:${event!!.values[0]}\nY ${event!!.values[1]}\nZ:${event!!.values[2]}"
      // double degrees=-((Math.atan2(event.values[0],event.values[1])*180/Math.PI )-90);
 var degree= 180-((atan2(event.values[0],event.values[1])*180.0)/ PI)-90.0
        textView2.text="$degree"
        seekBar.progress= (90-degree).toInt()
    }
}
