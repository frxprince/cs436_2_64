package com.drpaween.httppostdata

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import khttp.post
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 if (ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED)
     ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),1234)

  fun ShowResult(msg:String){
      this@MainActivity.runOnUiThread(Runnable {
          textView.text=msg
      })
       }

button.setOnClickListener {
    doAsync {
        var param= mapOf("param1" to editText.text.toString(),"param2" to editText2.text.toString())
        val res= post("https://www.drpaween.com/ohm/android/cgi1.php",params=param)
        Log.v("return",res.text)
      ShowResult(res.text)
    }
    }
}}
