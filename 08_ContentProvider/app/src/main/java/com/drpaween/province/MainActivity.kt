package com.drpaween.province

import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

textView.movementMethod= ScrollingMovementMethod()
var c=MakeCursor(0)

        while(c.moveToNext()){
            Log.v("from database",c.getString(0))
        }

        button.setOnClickListener {
            val cursor=contentResolver.query(Uri.parse("content://com.drpaween.thaiprovince"),null,"name",null,null)
            textView.text=""
            if(cursor!=null)
            {
                while(cursor.moveToNext())
                {
                 textView.text= textView.text.toString()+cursor.getString(0)+"\n"
                }
            }
        }
        button2.setOnClickListener {
            val cursor=contentResolver.query(Uri.parse("content://com.drpaween.thaiprovince"),null,"hello",null,null)
            textView.text=""
            if(cursor!=null)
            {
                while(cursor.moveToNext())
                {
                    textView.text= textView.text.toString()+cursor.getString(0)+"\n"
                }
            }
        }

    }


    fun MakeCursor(id : Int): Cursor {
        val cursor = MatrixCursor(arrayOf("name"))
var file=resources.openRawResource(R.raw.province)
var lines=file.bufferedReader(Charsets.UTF_8).readLines()
        for(line in lines)
        {
            cursor.addRow(arrayOf(line))
        }
return cursor
    }
}
