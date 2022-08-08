package com.wparja.deviceowner

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.util.*

class MainActivity : AppCompatActivity() {


   // private var mdpm: DPMImp? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        startServiceMyServices();
     //   mdpm = DPMImp()

        addpackageslocktask.setOnClickListener {
         //   mdpm?.setLockTaskPackages()

        }

        clearpackageslocktask.setOnClickListener {
           // mdpm?.clearTaskPackages()
        }

        addclientpackage.setOnClickListener {
//            if (mDevicePolicyManager?.isDeviceOwnerApp(packageName) == true) {
//                val packages = arrayOf<String>( packageName, "com.wparja.client" )
//                mDevicePolicyManager?.setLockTaskPackages(mAdminComponent!!, packages)
//            }
        }

        removeclientpackage.setOnClickListener {
//            if (mDevicePolicyManager?.isDeviceOwnerApp(packageName) == true) {
//                val packages = arrayOf<String>( packageName )
//                mDevicePolicyManager?.setLockTaskPackages(mAdminComponent!!, packages)
//            }
        }

        startlocktask.setOnClickListener {
          //  mdpm?.startLockTask(this)
        }

        stoplocktask.setOnClickListener {
          //  mdpm?.stopLockTask(this)
        }

        addhome.setOnClickListener {
          //  mdpm?.setDefaultHome()

        }

        removehome.setOnClickListener {
         //   mdpm?.removeLauncher()
        }


        startanotherappwithoutflags.setOnClickListener {
            val intent = applicationContext.packageManager.getLaunchIntentForPackage("com.wparja.client")
            startActivity(intent)
        }

        startanotherappwithflags.setOnClickListener {
            val intent = applicationContext.packageManager.getLaunchIntentForPackage("com.wparja.client")
//            if (mDevicePolicyManager?.isDeviceOwnerApp(packageName) == true) {
//                val packages = arrayOf<String>( "com.wparja.client" )
//                stopLockTask()
//                mDevicePolicyManager?.setLockTaskPackages(mAdminComponent!!, packages)
//            }
            startActivity(intent)
        }


    }

    private fun startServiceMyServices() {
        val intent = Intent(this, DeviceOwnerService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }


}