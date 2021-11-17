package com.example.smsremotecontroll

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.getStringExtra("mode")?:"" =="meaw")
        {
            Mp= MediaPlayer.create(this,R.raw.cat)
            Mp.start()
        }
        if (intent?.getStringExtra("mode")?:"" =="alarm")
        {
            Mp= MediaPlayer.create(this,R.raw.alarm)
            Mp.start()
        }

    }
lateinit var Mp:MediaPlayer
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

        button.setOnClickListener{finish()}

    }
}
