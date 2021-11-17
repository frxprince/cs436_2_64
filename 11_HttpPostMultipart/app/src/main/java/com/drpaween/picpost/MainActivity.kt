package com.drpaween.picpost

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.core.app.ActivityCompat
import khttp.extensions.fileLike
import khttp.extensions.post
import khttp.structures.files.FileLike
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
var bitmap: Bitmap?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode==4567) && (resultCode== Activity.RESULT_OK))
        {
            if (bitmap!=null){bitmap!!.recycle()}
            bitmap=data!!.extras!!.getParcelable<Bitmap>("data") as Bitmap
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
 if (ActivityCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED)
     ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET),1234)
textView.movementMethod=ScrollingMovementMethod()
    button.setOnClickListener {
        var i=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(i,4567)
    }

fun ShowResult(msg:String){
 this@MainActivity.runOnUiThread(Runnable { textView.text=msg })
}
        button2.setOnClickListener {

        doAsync{
                var file= createTempFile(prefix  ="photo",suffix = ".png")
                file.createNewFile()
                var bos = ByteArrayOutputStream()
                bitmap?.compress(Bitmap.CompressFormat.PNG, 99, bos)
                var fos=FileOutputStream(file)
                fos.write(bos.toByteArray())
                fos.flush()
                var file1=file.fileLike()
        var postfile= listOf(file1)

var res= khttp.post("https://www.drpaween.com/ohm/android/cgi2.php", files = postfile)
ShowResult(res.text)
                Log.v("file upload ",res.text)
                fos.close()
            file.delete()
            }
        }

    }
}
