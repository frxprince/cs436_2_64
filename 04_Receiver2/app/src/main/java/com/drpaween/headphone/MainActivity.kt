package com.drpaween.headphone

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{finish()}
      //  registerReceiver(MyReceiver(), IntentFilter(Intent.ACTION_HEADSET_PLUG))
if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS),1234)
}
    }
}
