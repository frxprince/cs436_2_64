package com.drpaween.UnitConversionV3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_result.*

class ShowResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)
        var intent=this.getIntent()
        textView3.text="""Input: ${ intent.getDoubleExtra("input",0.0)} 
            |   output: ${intent.getDoubleExtra("multiplier",1.0) * intent.getDoubleExtra("input",0.0)}
        """.trimMargin()
    }
}
