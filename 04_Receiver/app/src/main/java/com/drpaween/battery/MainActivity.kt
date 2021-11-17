package com.drpaween.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.max=100
        progressBar.progress=50
       var receiver=object: MyRecriver(){
           override fun update(batt_value: Float, Usb: Int) {
              progressBar.progress=batt_value.toInt()
            var UsbStatus=when(Usb){
                BatteryManager.BATTERY_STATUS_CHARGING->"Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING->"Discharging"
                BatteryManager.BATTERY_STATUS_FULL->"Full"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING->"Not charging"
                else->"Don't know"
            }
               textView.text="$Usb\n$UsbStatus"
           }
       }
        var myre=Myreceiver2()
        registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        registerReceiver(myre,IntentFilter(AudioManager.ACTION_HEADSET_PLUG))
   }


    abstract class MyRecriver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var level=intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)?:0
            var scale=intent?.getIntExtra(BatteryManager.EXTRA_SCALE,1)?:1
            var batt_value=(level/scale.toFloat())*100
            Log.v("battery","$batt_value")
            var charging=intent?.getIntExtra(BatteryManager.EXTRA_STATUS,0)?:0
            update(batt_value,charging)
        }
abstract fun update(batt_value:Float,Usb:Int)
    }

    class Myreceiver2:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
          Log.v("intent",AudioManager.ACTION_HEADSET_PLUG.toString())

        }

    }
}
