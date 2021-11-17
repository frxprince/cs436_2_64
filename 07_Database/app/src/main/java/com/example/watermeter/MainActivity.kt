package com.example.watermeter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var conn=MyDatabase(this,"water.sqlite",null,2)
        var DB=conn.writableDatabase
button.setOnClickListener{
    var id=editText.text.toString()
    var value=editText2.text.toString()
    DB.execSQL("insert into waterdata(meter_id,meter_value) values( $id,$value)")
    editText.setText("")
    editText2.setText("")

}
        button2.setOnClickListener {
            var i=Intent(this,Report::class.java)
            startActivity(i)
        }
    }
}
