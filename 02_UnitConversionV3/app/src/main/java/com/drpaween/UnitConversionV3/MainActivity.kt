package com.drpaween.UnitConversionV3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        setContentView(R.layout.activity_main)
        var animate=AnimationUtils.loadAnimation(this,R.anim.blink)
        textView.startAnimation(animate)
        textView.setOnClickListener({
            Log.v("myApp","Click!!!")

            var MainProgramIntent= Intent(this,MainProgram::class.java )
            startActivity(MainProgramIntent)
        })
    }
}
