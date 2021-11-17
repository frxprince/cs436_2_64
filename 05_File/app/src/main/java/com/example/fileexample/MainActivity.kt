package com.example.fileexample

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.style.CharacterStyle
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener() {
            var file = resources.openRawResource(R.raw.genesis01)
 var lines=file.bufferedReader(Charsets.UTF_8).readLines()
            var text=""
            for(line in lines)
                text="$text\n$line"
            editText.setText(text)
        }

        button2.setOnClickListener(){
            var file=assets.open("textfile/pangram.txt")
            var lines=file.bufferedReader(Charsets.UTF_8).readLines()
            var text=""
            for(line in lines)
                text="$text\n$line"
            editText.setText(text)

        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1234)
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)
        }

        button3.setOnClickListener(){
            var file=File(Environment.getExternalStorageDirectory().toString()+"/${editText2.text.toString()}")
            var outputfile=FileOutputStream(file)
            outputfile.write(editText.text.toString().toByteArray())
            outputfile.flush()
            outputfile.close()

        }
        button4.setOnClickListener(){
            var file=File(Environment.getExternalStorageDirectory().toString()+"/${editText2.text.toString()}")
            var lines=file.bufferedReader(Charsets.UTF_8).readLines()
            var text=""
            for(line in lines)
                text="$text\n$line"
            editText.setText(text)
        }
    }
}
