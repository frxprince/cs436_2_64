package com.drpaween.primeintentservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var receiver=object:MyReceiver(){
        override fun showProgress(progress: Int) {
            textView.text="$progress %"
        }

        override fun showResult(result: Long) {
            textView2.text="$result"
        }}


    override fun finish() {
        super.finish()
      unregisterReceiver(receiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            var i=Intent(this,PrimeIntentService::class.java)
            i.putExtra("k",editText.text.toString().toLong())
            i.setAction("start")
            startService(i)
        }
        button2.setOnClickListener { finish() }
        if (intent.hasExtra("result"))
            textView2.text=intent.getLongExtra("result",0).toString()
        registerReceiver(  receiver  , IntentFilter("com.drpaween.primek.progress"))
    }

   abstract class MyReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
if (intent!!.getStringExtra("mode")=="progress")
    showProgress(intent!!.getIntExtra("progress",0))
            if (intent!!.getStringExtra("mode")=="result")
                showResult(intent!!.getLongExtra("result",0))
        }
        abstract fun showProgress(progress:Int)
       abstract fun showResult(result:Long)
   }
}
