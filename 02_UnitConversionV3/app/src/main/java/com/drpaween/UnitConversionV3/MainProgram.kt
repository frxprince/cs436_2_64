package com.drpaween.UnitConversionV3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_program.*

class MainProgram : AppCompatActivity() {
var multiplier=1.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        setContentView(R.layout.activity_main_program)
        button.setOnClickListener({
            var UnitSelectorIntent= Intent(this,UnitSelector::class.java)
            startActivityForResult(UnitSelectorIntent,1234)
        })

        button2.setOnClickListener({
            var intent=Intent(this,ShowResult::class.java)
            intent.putExtra("input",editText.text.toString().toDouble())
            intent.putExtra("multiplier",multiplier)
            startActivity(intent)
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
            if(requestCode == 1234){
                when(data!!.getStringExtra("data")) {
                    "kb" -> {
                        button.setText("kb")
                        multiplier=1/1000.0
                    }
                    "mb" -> {
                        button.setText("mb")
                        multiplier=1/1000000.0
                    }
                    "gb" -> {
                        button.setText("kb")
                        multiplier=1/1000000000.0
                    }
                    else -> button.setText("XXX")
                }

            }
    }
}
