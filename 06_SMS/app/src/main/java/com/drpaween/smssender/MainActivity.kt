package com.drpaween.smssender

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
  if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
  {
      ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE),1234)
  }
        var sentPI=PendingIntent.getBroadcast(this,0, Intent("SMS_SENT"),0)
        var deliveredPI=PendingIntent.getBroadcast(this,0, Intent("SMS_DELIVERED"),0)


        button.setOnClickListener{
       var sms= SmsManager.getDefault()
       sms.sendTextMessage(editText3.text.toString(),null,editText2.text.toString(),sentPI,deliveredPI);

   }
//var sms_sent=SMSsent()
registerReceiver(SMSsent(),  IntentFilter("SMS_SENT"))
        registerReceiver(SMSdelivered(),IntentFilter("SMS_DELIVERED"))




    }


    class SMSsent:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.v("Hello SMS","Sent...")
            when(resultCode){
                Activity.RESULT_OK->
                    Toast.makeText(context, "The SMS is sent",
                        Toast.LENGTH_LONG).show()
                SmsManager.RESULT_ERROR_GENERIC_FAILURE->
                    Toast.makeText(context, "The SMS is error (generic failure)",
                        Toast.LENGTH_LONG).show()
                SmsManager.RESULT_ERROR_NO_SERVICE->
                    Toast.makeText(context, "The SMS is error (no service)",
                        Toast.LENGTH_LONG).show()
                SmsManager.RESULT_ERROR_RADIO_OFF->
                    Toast.makeText(context, "The SMS is error (flight mode)",
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    class SMSdelivered:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.v("Hello SMS","Delivered...")
            when(resultCode){
                Activity.RESULT_OK->
                    Toast.makeText(context, "The SMS is delivered",
                        Toast.LENGTH_LONG).show()
                else->
                    Toast.makeText(context, "The SMS is fail",
                        Toast.LENGTH_LONG).show()
            }

        }
    }
}
