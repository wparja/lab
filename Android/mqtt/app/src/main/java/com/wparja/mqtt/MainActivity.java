package com.wparja.mqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MQTTHelper.getInstance().init("192.168.0.136");
        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                Log.d("T", service.service.getClassName());
            }
        }
    //    MQTTHelper.getInstance().MQTTClient().publish(1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MQTTHelper.getInstance().disco();
    }

    public void onClick(View view) {

        if (view.getId() == R.id.qos1) {
            MQTTHelper.getInstance().MQTTClient().publish(1);
        } else if (view.getId() == R.id.qos2) {
            MQTTHelper.getInstance().MQTTClient().publish(2);
        } else {
            EditText ip = findViewById(R.id.ip);
           // MQTTHelper.getInstance().init(ip.getText().toString().trim());
            MQTTHelper.getInstance().reconnect();
        }
    }
}