package com.drpaween.primefinder2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.max=100

        button.setOnClickListener {
            doAsync{
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
                        {  c++
                            uiThread { progressBar.setProgress(Math.round((c / x.toFloat()) * 100) as Int) }

                            if(c==x)return i
                        }
                        i++
                    }
                    return 0
                }
                var result=primeK(editText.text.toString().toLong())
                uiThread {
                    textView.text="$result"
                }
            }
        }
    }
}
