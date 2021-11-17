package com.example.smsremotecontroll

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log

class MySMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.v("My Application","Receiveing!!@!")
var message=Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]


        var i=Intent(context,MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        if (message.displayMessageBody == "meaw" )
        {

            i.putExtra("mode","meaw")
            context.startActivity(i)
            context.startActivity(i)
        }

        if (message.displayMessageBody == "alarm" )
        {

            i.putExtra("mode","alarm")
            context.startActivity(i)
            context.startActivity(i)
        }
    }
}
