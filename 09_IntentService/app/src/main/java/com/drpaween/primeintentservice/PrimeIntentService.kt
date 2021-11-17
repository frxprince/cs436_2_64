package com.drpaween.primeintentservice

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat


class PrimeIntentService : IntentService("PrimeIntentService") {
var intent_progress=Intent()

    override fun onHandleIntent(intent: Intent?) {
        Log.v("my intent service","Start on handle intent")
        when (intent?.action) {
            "start" -> {
                val param1 = intent.getLongExtra("k",0)
                handleActionStart(param1)
            }
        }
    }

    private fun handleActionStart(k:Long) {
        Log.v("my intent service","algorithm start $k")
        intent_progress.setAction("com.drpaween.primek.progress")
        var result=primeK(k)
        var i=Intent(this,MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // need for calling outside activity
        i.putExtra("result",result)
        ContextCompat.startActivity(this,i,null)
        intent_progress.putExtra("mode","result")
        intent_progress.putExtra("result",result)
        sendBroadcast(intent_progress)
        Log.v("my intent service","algorithm result= $result")
    }


    fun isPrime(x:Long):Boolean{
        for(i in 2..x-1)
            if ((x%i) == 0L)
                return false
        return true
    }

    fun primeK(x:Long):Long{
        var i=3L
        var c=0L
        while(true){
            if(isPrime(i))
            {
                c++
                intent_progress.putExtra("progress",Math.round((c / x.toFloat()) * 100) as Int)
                intent_progress.putExtra("mode","progress")
                sendBroadcast(intent_progress)
               // ShowProgress(Math.round((c / x.toFloat()) * 100) as Int)
                if(c==x)
                    return i
            }
            i++
        }
        return 0
    }
}
