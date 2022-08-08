package com.wparja.deviceowner

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.util.*

class DeviceOwnerService : Service() {

    private val DATA_RECEIVED_BY_COLLECT_FORWARD = "action_data_received"
    private val EXTRA_DATA_RECEIVED = "extra_data_received"

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }


    override fun onCreate() {
        super.onCreate()

        val intent = applicationContext.packageManager.getLaunchIntentForPackage("com.wparja.client")
        startActivity(intent)
        val buffer = ByteArray(1024 * 1024)
        val timer = Timer()
        timer.schedule(object: TimerTask() {
            override fun run() {
                sendFile(writeBundleIntoFile(buffer))
            }
        }, 20000);
    }

    @Throws(IOException::class)
    private fun writeBundleIntoFile(bytes: ByteArray): File {
        val fileDir = filesDir
        val file =File(fileDir, "bundle.data")
        val out = ObjectOutputStream(FileOutputStream(file))
        out.writeObject(bytes)
        out.flush()
        out.close()
        return file
    }
    private fun sendFile(file: File) {
        val uri: Uri = FileProvider.getUriForFile(applicationContext,"com.hexagonmining.service.fileprovider", file)
        val i = Intent("action_bundle_request_response")
        i.putExtra("bundle_uri", uri)
        grantUriPermission("com.wparja.client", uri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        )
        sendBroadcast(i)
    }

}