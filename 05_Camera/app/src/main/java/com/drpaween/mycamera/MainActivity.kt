package com.drpaween.mycamera

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.util.*
import java.util.jar.Manifest

/*
 Screen always recreated on every orientation change!!!



 */
class MainActivity : AppCompatActivity() {
 var Image:Bitmap?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("camera","Return!")
        if (requestCode==1234 && resultCode== Activity.RESULT_OK){
            if(Image!=null)Image?.recycle()
            Image=data?.extras?.getParcelable<Bitmap>("data")

            var canvas=Canvas(Image!!)
            var paint= Paint()
            paint.setColor(Color.MAGENTA)
            paint.textSize=8F
            paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_OVER))
            canvas.drawBitmap(Image!!,0F,0F,paint)
            canvas.drawText( Calendar.getInstance().time.toString() ,1F,20F,paint)


            imageView.setImageBitmap(Image)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("camera","Create!")

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),4567)


        imageView.setOnClickListener(){
            var cameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent,1234)
        }
        button.setOnClickListener(){
            if(Image!=null) {
                var file = File(Environment.getExternalStorageDirectory().toString()+"/DCIM/"+ editText.text.toString())

                file.createNewFile()
                var bos = ByteArrayOutputStream()
                Image?.compress(Bitmap.CompressFormat.PNG, 99, bos)
                var fos=FileOutputStream(file)
                fos.write(bos.toByteArray())
                fos.flush()
                fos.close()
                Log.v("camera","Saved!"+ file.absolutePath.toString())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if((Image?.width?:0) <10){
            imageView.setImageBitmap(Image)
            Log.v("camera","redraw")
        }
    }
}
