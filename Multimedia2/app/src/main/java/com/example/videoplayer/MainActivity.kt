package com.example.videoplayer

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.MediaController
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
videoView.setMediaController(MediaController(this))
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
          ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1234)
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.INTERNET),1234)
        }

        button.setOnClickListener(){
            videoView.setVideoURI(Uri.parse("android.resource://"+packageName+"/"+R.raw.video2))

        }

        button2.setOnClickListener(){
            videoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().path+"/DCIM/Loituma.3gp"))
        }
        button3.setOnClickListener(){
            videoView.setVideoURI(Uri.parse("http://www.drpaween.com/ohm/cs436/mv.mp4"))
        }
        button4.setOnClickListener(){
            videoView.start()
        }
        button5.setOnClickListener(){
            videoView.pause()
        }
        button6.setOnClickListener(){
            videoView.stopPlayback()
        }
    }
}
