package com.example.httprequest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() ,AdapterView.OnItemSelectedListener{
lateinit var rate_map:JSONObject
    var currency_list= mutableListOf<String>()
    var THB_to_EURO:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


fun HttpGetDone(){
    this@MainActivity.runOnUiThread(
        Runnable {var adapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,currency_list)
            spinner.adapter=adapter
            spinner.onItemSelectedListener=this
            }
    )
}
        doAsync {
            var url = URL("https://api.exchangeratesapi.io/latest")
            var conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()
            var response = BufferedReader(InputStreamReader(conn.inputStream)).readLine()
            var exchange_rate = JSONObject(response)
            var curency = exchange_rate.getString("rates")
            rate_map=JSONArray("["+curency+"]").getJSONObject(0)

for (i in rate_map.keys()){
    Log.v("json key pair","  $i   ->    ${rate_map.getString(i)}")
    if (i=="THB")
        THB_to_EURO=rate_map.getDouble(i)
    currency_list.add(i)}
            HttpGetDone()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        var result=(editText.text.toString().toDouble() / THB_to_EURO)*rate_map.getDouble(currency_list[p2])
        textView2.text="$result ${currency_list[p2]}"
    }
}