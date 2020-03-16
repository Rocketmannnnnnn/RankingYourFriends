package com.example.rankingyourfriends.MQTT;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Messenger {
    private Context context;
    private IMessageReceiver receiver;
    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;
    private MyBroadcastReceiver myBroadCastReceiver;
    private static final String BROADCAST_ACTION = "com.example.mqttpayloadavailabe";

    public Messenger(Context context, IMessageReceiver receiver) {
        this.context = context;
        this.receiver = receiver;
    }

    public void sendMessage(String payload){
        try {
            this.pahoMqttClient.publishMessage(this.client, payload, 0, MQTTConfig.getInstance().PUBLISH_TOPIC());
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //Subscribe to the given topic (game room)
    public void subscribe(String topic){
        MQTTConfig.getInstance().setMQTT_TOPIC(topic);
        try {
            this.pahoMqttClient.subscribe(this.client, MQTTConfig.getInstance().PUBLISH_TOPIC(), 0);
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(this.context, "Subscribing error",Toast.LENGTH_LONG).show();
        }
    }

    //Unsubscribe from the game room
    public void unsubscribe(){
        try {
            pahoMqttClient.unSubscribe(client, MQTTConfig.getInstance().PUBLISH_TOPIC());
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(this.context, "Unsubscribing error",Toast.LENGTH_LONG).show();
        }
    }



    // Defineer een eigen broadcast receiver, deze vangt alles op
    public class MyBroadcastReceiver extends BroadcastReceiver {

        private final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String payload = intent.getStringExtra("payload");
                Log.i(TAG, payload);

                try{
                    JSONObject object = new JSONObject(payload);
                    receiver.OnMessageReceived(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                try {
//                    JSONObject jsonObject = new JSONObject(payload);
//                    String message = jsonObject.getJSONObject("object").getString("message");
//                    Log.d("onReceive", message);
//                    String[] words = message.split("%");
//                    message = "";
//                    for(String word : words){
//                        message += word + " ";
//                    }
//                    Log.d("onReceive", message);
//                    messages.add(0, message);
//                    messageAdapter.notifyDataSetChanged();
//                    Log.i(TAG, message.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
