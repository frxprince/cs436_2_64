package com.example.intentdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
//https://developer.android.com/guide/components/intents-common
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


if(ContextCompat.checkSelfPermission(this, "com.android.alarm.permission.SET_ALARM")!=PackageManager.PERMISSION_GRANTED){
     ActivityCompat.requestPermissions(this, arrayOf("com.android.alarm.permission.SET_ALARM"),1234)
        }

        button.setOnClickListener {
            var intent=Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mju.ac.th"))
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }else
            {
                button.text="Not support"
                button.isEnabled=false
            }
        }

        button2.setOnClickListener {
            var intent=Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE,"Time's up")
                putExtra(AlarmClock.EXTRA_LENGTH,10)
                putExtra(AlarmClock.EXTRA_SKIP_UI,true)
            }
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }else{
                button2.text="Not support"
                button2.isEnabled=false
            }
        }


        button3.setOnClickListener {
            var intent=Intent(Intent.ACTION_VIEW,Uri.parse("geo:18.8940254,99.022228"))
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(intent)
            }else{
                button3.text="Not support"
                button3.isEnabled=false
            }
        }
    }
}
