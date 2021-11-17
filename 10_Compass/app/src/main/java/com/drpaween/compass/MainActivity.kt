package com.drpaween.compass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.atan2

class MainActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener ,SensorEventListener{
lateinit var sensor:Sensor
    lateinit var manager:SensorManager
    override fun onPause() {
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       seekBar.setOnSeekBarChangeListener(this)
seekBar.max=360
  seekBar.progress=180
        manager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor=manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    }

    override fun onResume() {
        super.onResume()

      manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean){
    imageView.rotation  =  p0!!.progress.toFloat()-180
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(p0: SensorEvent?) {
      var x=p0!!.values[0]
        var y=p0!!.values[1]
        var degree=((-atan2(y,x) / PI)*180)
        textView.text=degree.toString()
        Log.v("degree",degree.toString())
       imageView.rotation  = ( degree).toFloat()-180
    }
}
