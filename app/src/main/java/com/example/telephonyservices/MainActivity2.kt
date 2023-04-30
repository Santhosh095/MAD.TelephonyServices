package com.example.telephonyservices

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity2 : AppCompatActivity() {
    private val req = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()

        val tvText : TextView = findViewById(R.id.textView5)
        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val networkOperatorName = telephonyManager.networkOperatorName
        val phoneType: Int = telephonyManager.phoneType
        var strphoneType = ""
        val networkCountryISO: String = telephonyManager.networkCountryIso
        val simCountryISO: String = telephonyManager.simCountryIso
        val softwareVersion: String? = telephonyManager.deviceSoftwareVersion

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_PHONE_STATE) !=
            PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_PHONE_STATE), req)
        }

        when (phoneType) {
            TelephonyManager.PHONE_TYPE_CDMA -> strphoneType = "CDMA"
            TelephonyManager.PHONE_TYPE_GSM -> strphoneType = "GSM"
            TelephonyManager.PHONE_TYPE_NONE -> strphoneType = "NONE"
        }
        val networkInfo = getString(R.string.network_info, networkOperatorName, strphoneType, networkCountryISO, simCountryISO, softwareVersion)
        tvText.text = networkInfo

    }
}