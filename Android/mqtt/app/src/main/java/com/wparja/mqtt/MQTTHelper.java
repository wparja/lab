package com.wparja.mqtt;


public class MQTTHelper {

    private static MQTTHelper instance = new MQTTHelper();
    public static MQTTHelper getInstance() {return instance;}

    private MqttAsyncWrapper mqttClient;

    private MQTTHelper() {

    }

    public void init(String ip) {
        mqttClient = new MqttAsyncWrapper(ip);
    }

    public MqttAsyncWrapper MQTTClient() {
        return mqttClient;
    }

    public void reconnect() {
        mqttClient.reconnect();
    }

    public void disco() {
        mqttClient.disconnect();
    }


}
