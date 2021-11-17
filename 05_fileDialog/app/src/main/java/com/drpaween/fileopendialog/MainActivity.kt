package com.drpaween.fileopendialog

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("return","back")
        var uri=data?.data as Uri
        textView.text= uri.toString()+"\n"+uri?.path?:"no data" + "\n"+ MediaURItoPath(uri)

    }

    fun MediaURItoPath(uri:Uri):String{
        var proj= arrayOf(MediaStore.Images.Media.DATA )
        Log.v("stringxxx",MediaStore.Images.Media.DATA)
        var cursor=contentResolver.query(uri,proj,null,null,null)
        var col_index=cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) as Int
        cursor?.moveToFirst()
        var path=cursor.getString(col_index)
        cursor.close()
        return path
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            var i= Intent()
            i.setType("*/*")
            i.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(i,"Please select a file"),1234)
        }
    }
}
