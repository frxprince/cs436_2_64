package com.drpaween.myapplication

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.audiofx.Visualizer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {

    lateinit var Mp:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1234)
    }
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),1234)
        }
        button.setOnClickListener(){
            Mp=MediaPlayer()
            Mp= MediaPlayer.create(this,R.raw.greeting)

        }
        button2.setOnClickListener(){
Mp= MediaPlayer()
                var mp3file=assets.openFd("mp3/tada.mp3")
Mp.setDataSource(mp3file.fileDescriptor,mp3file.startOffset,mp3file.length)
           Mp.prepare()

        }

        button3.setOnClickListener(){
            Mp= MediaPlayer()
            Mp.setDataSource(Environment.getExternalStorageDirectory().path+"/DCIM/song.mp3")
            Mp.prepare()
            Log.v("myapp",Environment.getExternalStorageDirectory().path+"/DCIM/song.mp3")
        }
button4.setOnClickListener(){
    Mp= MediaPlayer()
    try {
        Mp.setDataSource(this, Uri.parse("http://112.121.150.133:8282/;stream"))
        Mp.prepare()
    } catch (e: Exception) {
        Mp= MediaPlayer.create(this,R.raw.greeting)
    } finally {

    }
}
button5.setOnClickListener(){
    Mp.start()
}
   button6.setOnClickListener(){
       Mp.pause()
   }
        button7.setOnClickListener(){
            Mp.stop()
        }
    }
}
