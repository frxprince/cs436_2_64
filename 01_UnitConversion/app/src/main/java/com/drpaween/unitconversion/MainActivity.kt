package com.drpaween.unitconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    button.setOnClickListener {

        try {
            var ans: Double = editText?.text.toString().toDouble()?:0 * 2.54
            Log.v("my debug", editText.text.toString())
            textView.text = ans.toString()+" inch"
        }catch(e: NumberFormatException){
            textView.text = "Error input is not a number"
        }
    }
        button3.setOnClickListener {
            try{
            var ans:Double=   editText.text.toString().toDouble()/2.54
            Log.v("my debug",editText.text.toString())
            textView.text="${"%.3f".format(ans)} cm"
            }catch (e: NumberFormatException ){
                textView.text = "Error input is not a number"
            }
        }
        button2.setOnClickListener { finish() }
    }
}
