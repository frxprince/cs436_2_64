package com.drpaween.preferencesdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button3.setOnClickListener(){
            finish()
        }

        button2.setOnClickListener(){
            var preferences=getSharedPreferences("my_config", Context.MODE_PRIVATE)
            preferences.edit {
                putString("param1",editText.text.toString())
                putInt("param2",editText2.text.toString().toInt())
                putString("param3",editText3.text.toString())
                commit()
            }

        }
        button.setOnClickListener(){
            var preferences=getSharedPreferences("my_config", Context.MODE_PRIVATE)
            var all_preference=preferences.all
            editText.setText(preferences.getString("param1","no data"))
          editText2.setText(all_preference.get("param2")?.toString()?:"0")
            editText3.setText(all_preference.get("param3")?.toString()?:"no data")
        }
    }
}
