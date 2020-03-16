package com.example.rankingyourfriends.MQTT;

import org.json.JSONObject;

public interface IMessageReceiver {
    void OnMessageReceived(JSONObject message);
}
