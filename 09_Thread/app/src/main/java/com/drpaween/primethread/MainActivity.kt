package com.drpaween.primethread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.round

class MainActivity : AppCompatActivity() {
lateinit var worker:PrimeKThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
             worker=object: PrimeKThread(editText.text.toString().toLong()){
                override fun ShowProgress(progress: Int) {
                    this@MainActivity.runOnUiThread(Runnable{textView.text="$progress%"})
                }

                override fun ShowResult(result: Long) {
                    this@MainActivity.runOnUiThread { textView2.text="$result" }
                }
            }
            worker.start()

        }
        button2.setOnClickListener { worker.running=false }
    }

  abstract   class PrimeKThread(val k:Long):Thread(){
      var running=true
        override fun start() {
            super.start()
        }

        override fun run() {
            super.run()
            val result=primeK(k)
            ShowResult(result)

        }
      abstract fun ShowProgress(progress:Int)
      abstract fun ShowResult(result:Long)

        fun isPrime(x:Long):Boolean{

            for(i in 2..x-1)
                if ((x%i) == 0L)
                    return false
            return true
        }

        fun primeK(x:Long):Long{
            var i=3L
            var c=0L
            while(running){
                if(isPrime(i))
                {
                    c++
                    ShowProgress(round((c/x.toFloat()) *100) as Int)
                    if(c==x)
                        return i
                }
                i++
            }
return 0
    }
    }
}
