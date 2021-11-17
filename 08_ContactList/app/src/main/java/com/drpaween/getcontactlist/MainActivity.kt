package com.drpaween.getcontactlist

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            var projection= arrayOf(ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER,ContactsContract.Contacts._ID)
            var uri:Uri=data?.data?:Uri.parse("")

            var cursor= managedQuery(uri,projection,null,null,null)
            cursor!!.moveToFirst()
            var id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            var name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            var hasPhone=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
            textView.text=name
            textView2.text="no data"
            if (hasPhone.equals("1",true))
            {
     var cursor2=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
         ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+id,null,null)
                cursor2!!.moveToFirst()
                var phone=cursor2.getString(cursor2.getColumnIndex("data1"))
                textView2.text=phone
                cursor2.close()
            }
cursor.close()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            var i = Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
            startActivityForResult(i,1234)
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),456)
    }
}
