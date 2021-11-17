package com.drpaween.gps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,LocationListener,OnNmeaMessageListener{
lateinit var gps:LocationManager
    override fun onPause() {
        super.onPause()
        gps.removeUpdates(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1234)
gps=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        gps.addNmeaListener(this)
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1234)}else {
            gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this)

        }
    }

    override fun onLocationChanged(location: Location?) {
        textView.text="Lat:${location!!.latitude}\nLong:${location.longitude}\nAltitude:${location.altitude}\nSpeed:${location.speed}\nBearing:${location.bearing}\n:Atomic clock:${location.time}     "

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

    override fun onNmeaMessage(message: String?, timestamp: Long) {
        textView2.text=message +"\n"+textView2.text
    }


}
