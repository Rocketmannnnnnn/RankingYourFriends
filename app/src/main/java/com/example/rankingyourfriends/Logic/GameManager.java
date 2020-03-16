package com.example.rankingyourfriends.Logic;

import android.content.Context;
import android.util.Log;

import com.example.rankingyourfriends.MQTT.IMessageReceiver;
import com.example.rankingyourfriends.MQTT.Messenger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class GameManager implements IMessageReceiver {

    private PayloadSender sender;
    private PayloadReceiver receiver;
    private Messenger messenger;
    private Context context;

    private final String TAG = "GameManager";

    public static GameManager INSTANCE;
    private GameManager(){}
    public static GameManager getInstance(){
        if(INSTANCE != null){
            return INSTANCE;
        }else {
            INSTANCE = new GameManager();
            return INSTANCE;
        }
    }

    public void newGame(String name, boolean isHost, String room, Context context){
        this.sender = new PayloadSender(name, isHost);
        this.receiver = new PayloadReceiver();
        this.messenger = new Messenger(context, this);
        this.messenger.subscribe(room);
        this.context = context;
    }

    public void sendJoinMessage(){
        try {
            this.messenger.sendMessage(sender.getLoginPayload().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendVote(String otherPlayer){
        try {
            this.messenger.sendMessage(this.sender.getVotePayload(otherPlayer).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendLeave(){
        try {
            this.messenger.sendMessage(this.sender.getLeavePayload().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.messenger.unsubscribe();
    }

    public void sendResult(String result){
        try {
            this.messenger.sendMessage(this.sender.getResultPayload(result).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendQuestion(String question){
        try {
            this.messenger.sendMessage(this.sender.getQuestionPayload(question).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendPlayerList(ArrayList<String> players){
        try {
            this.messenger.sendMessage(this.sender.getPlayerListPayload(players).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnMessageReceived(JSONObject message) {
        try{
            switch (message.getString(PayloadConfig.type)){
                default:
                    Log.i(TAG, "OnMessageReceived");
            }
        } catch (JSONException ex){
            ex.printStackTrace();
        }
    }
}
