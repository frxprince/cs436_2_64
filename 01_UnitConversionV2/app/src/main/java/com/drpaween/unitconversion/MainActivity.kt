package com.drpaween.unitconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
button.setOnClickListener{
    var ans:Double=0.0
    ans= if(radioButton.isChecked) editText.text.toString().toDouble()*2.54 else editText.text.toString().toDouble()/ 2.54
 textView.text= "%.4f".format(ans)+ " " + if(radioButton.isChecked) resources.getString(R.string.cm) else resources.getString((R.string.inch))
}
    }
}
