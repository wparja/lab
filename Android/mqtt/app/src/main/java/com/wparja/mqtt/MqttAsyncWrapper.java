package com.wparja.mqtt;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class MqttAsyncWrapper {

    private String topic_qos1 = "foo/";
    private String topic_qos2 = "wagner/qos2";


    private MqttAsyncClient mMqttAsyncClient;
    private HashMap<String, receiveCallback> mReceivers = new HashMap<>();
    private String mBrokerIP;
    private Message mMessagePublish;
    private Gson mGson = new Gson();


    private MqttConnectOptions createMqttOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        options.setAutomaticReconnect(true);
        options.setUserName("platform");
        options.setPassword("*RV8ed3460b9h6*R[".toCharArray());
        return options;
    }

    @SuppressLint("DefaultLocale")
    public MqttAsyncWrapper(String brokerIP) {
        try {
            mBrokerIP = brokerIP;
            createMessageToPublish(2048);
            connect();
        } catch (Exception e) {
        }
    }

    private void createMessageToPublish(int messageSize) {
        mMessagePublish = new Message();
        mMessagePublish.payload = new byte[messageSize];
        new Random().nextBytes(mMessagePublish.payload);
    }

    private void connect() {
        try {
            String clientId = "MobilePlatform_";
            mMqttAsyncClient = new MqttAsyncClient(String.format("tcp://%s:%d", mBrokerIP, 1883), clientId, new MemoryPersistence());
            mMqttAsyncClient.setCallback(mMqttCallback);
            mMqttAsyncClient.connect(createMqttOptions(), this, mIMqttActionListener);
        } catch (MqttException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    public void reconnect() {
        try {
            mMqttAsyncClient.reconnect();
        } catch (Exception ex) {
            Log.d("RT", ex.getMessage());
        }
    }

    public boolean isConnect() {
        return mMqttAsyncClient.isConnected();
    }

    public void subscribe(String topic, receiveCallback receiveCallback) {
        mReceivers.put(topic, receiveCallback);
        try {
            mMqttAsyncClient.subscribe(topic, 0);
        } catch (Exception e) {
        }
    }

    public void unSubscribe(String topic) {
        try {
            mMqttAsyncClient.unsubscribe(topic);
            mReceivers.remove(topic);
        } catch (Exception e) {
        }
    }

    public boolean publish(int q) {
        int qos = 1;
        if (mMqttAsyncClient.isConnected()) {

//            try {
//                Thread thread = new Thread() {
//                    @Override
//                    public void run() {
//                        while (true) {
//                            for (int i = 0; i < 1200; i++) {
//                                String topic = topic_qos1 + i;
//                                for (int j = 0; j < 5; j++) {
//                                    mMessagePublish.id = SequenceGenerator.getInstance().nextId();
//                                    String payload = mGson.toJson(mMessagePublish);
//                                    MqttMessage mqttMessage = new MqttMessage();
//                                    mqttMessage.setQos(qos);
//                                    mqttMessage.setPayload(payload.getBytes());
//                                    try {
//                                        mMqttAsyncClient.publish(topic, mqttMessage);
//                                        Thread.sleep(1);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        }
//                    }
//                };
//                thread.start();



//            } catch (Exception e) {
//                Log.d("TAG", e.getMessage());
//            }
            return true;
        }
        return false;
    }

    private IMqttActionListener mIMqttActionListener = new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
            reSubscriber();
            publish(1);
        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
            scheduleConnection();
        }
    };


    private void reSubscriber() {
        for (Map.Entry<String, receiveCallback> entry : mReceivers.entrySet()) {
            try {
                mMqttAsyncClient.subscribe(entry.getKey(), 0);
            } catch (Exception e) {
            }
        }
    }

    private void scheduleConnection() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                connect();
            }
        },1000);
    }

    private MqttCallback mMqttCallback = new MqttCallbackExtended() {
        @Override
        public void connectComplete(boolean reconnect, String serverURI) {
            reSubscriber();
        }

        @Override
        public void connectionLost(Throwable cause) {
            String error = "Mqtt connectionLost: " + cause.getMessage();

        }

        @Override
        public void messageArrived(String topic, MqttMessage message) {
            try {
                if (mReceivers.containsKey(topic)) {
                    mReceivers.get(topic).processMessage(message.getPayload());
                }
            } catch (Exception e) {
                String error = "";
                if (e.getMessage() != null) {
                    error = e.getMessage();
                }
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
        }
    };

    public void disconnect() {
        try {
            mMqttAsyncClient.disconnectForcibly();
            mMqttAsyncClient.close();
        } catch (Exception e) {
        }
    }

    public interface receiveCallback {
        void processMessage(byte[] payload);
    }

    private class Message {
        long id;
        byte[] payload;
    }
}
