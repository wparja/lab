package com.wparja.client

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class ClientService : Service() {

    private val DATA_RECEIVED_BY_COLLECT_FORWARD = "action_data_received"

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }


    override fun onCreate() {
        super.onCreate()
        applicationContext.registerReceiver(
            mBroadcastReceiverBundleRequest,
            IntentFilter("action_bundle_request_response"))
       // Thread.sleep(30000);
    }

    var mBroadcastReceiverBundleRequest: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val uri = intent.getParcelableExtra<Uri>("bundle_uri")
            Toast.makeText(context,"OK - " + uri.toString() + System.currentTimeMillis(),Toast.LENGTH_SHORT ).show()
            Log.d("TAG1", "received")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext.unregisterReceiver(mBroadcastReceiverBundleRequest)
    }
}