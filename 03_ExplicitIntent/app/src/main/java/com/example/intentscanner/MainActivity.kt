package com.example.intentscanner

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var displayList= mutableListOf<String>()
        var package_list= packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (item in package_list){
            Log.v("item",item.packageName +" , " +item.className)
        displayList.add(item.packageName)
        }
    val adapter=ArrayAdapter(this,R.layout.activity_listitem,displayList.toTypedArray())

listview_1.adapter=adapter


        listview_1.setOnItemClickListener(){ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->

                textView.text=listview_1.getItemAtPosition(i).toString()



        if (packageManager.getLaunchIntentForPackage(textView.text.toString()) !=null){
            button2.isEnabled=true
            textView2.text=packageManager.getLaunchIntentForPackage(textView.text.toString()).toString()
        }else {
            button2.isEnabled = false
            textView2.text="No launcher"
        }
        }

        button2.setOnClickListener(){
           startActivity(packageManager.getLaunchIntentForPackage(textView.text.toString()))
           // var i= packageManager.getLaunchIntentForPackage("com.google.android.youtube")



         //   startActivity(i)

        }
    }
}
