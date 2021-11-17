package com.drpaween.UnitConversionV3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_unit_selector.*

class UnitSelector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_down,R.anim.slide_up)
        setContentView(R.layout.activity_unit_selector)
        var intent= Intent()
        button3.setOnClickListener(
            {

                intent.putExtra("data","kb")
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        )

        button4.setOnClickListener(
            {

                intent.putExtra("data","mb")
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        )

        button5.setOnClickListener(
            {

                intent.putExtra("data","gb")
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        )

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_down,R.anim.slide_up)
    }
}
