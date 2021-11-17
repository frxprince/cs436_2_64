package com.drpaween.smsreceiver

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.TextView
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
/*
This app do not work in windows closed

 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 1234)
        }
    var receiver=object: SMSReceiver(){
        override fun resultReady(msg: String, phoneNo: String) {
            textView.text="Message from:$phoneNo\n  $msg"
        }

    }

    registerReceiver(receiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }

    private abstract class SMSReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var message=Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
            resultReady(message.displayMessageBody,message.displayOriginatingAddress)
        }
        abstract fun resultReady(msg:String,phoneNo:String)

    }
}