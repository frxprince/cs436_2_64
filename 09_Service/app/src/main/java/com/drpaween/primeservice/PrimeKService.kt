package com.drpaween.primeservice

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import androidx.core.content.ContextCompat

class PrimeKService : Service() {
    var CHANNEL_ID = "ForegroundService Kotlin"
companion object{
    fun startService(context: Context, k: Long) {
        val startIntent = Intent(context, PrimeKService::class.java)
        startIntent.putExtra("k", k)
        ContextCompat.startForegroundService(context, startIntent)
    }
    fun stopService(context: Context) {
        val stopIntent = Intent(context, PrimeKService::class.java)
        context.stopService(stopIntent)
    }
}
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

        Log.v("my service","onstartcommand")
        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        startXForeground()
        Log.v("my service","start")
    }


    override fun onBind(intent: Intent): IBinder? {
        return null
    }



    private fun startXForeground() {
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                // If earlier version channel ID is not used
                // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                ""
            }

        val notificationBuilder = NotificationCompat.Builder(this, channelId )
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(101, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }





    }

