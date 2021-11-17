package com.drpaween.simpleform

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    val province= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cursor=contentResolver.query(Uri.parse("content://com.drpaween.thaiprovince"),null,"name",null,null)
        if (cursor!=null){
            while (cursor.moveToNext()){
                Log.v("query test",cursor.getString(0))
            province.add(cursor.getString(0))
            }
            var adapter=ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,province)
           spinner.adapter=adapter
        }
spinner.onItemSelectedListener=this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       textView.text=   province.get(position) + "  ID=$position"
    }
}
