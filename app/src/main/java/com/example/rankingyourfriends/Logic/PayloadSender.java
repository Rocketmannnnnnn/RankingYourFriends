package com.example.rankingyourfriends.Logic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PayloadSender {

    private String name;
    private Boolean isHost;

    public PayloadSender(String name, Boolean isHost) {
        this.name = name;
        this.isHost = isHost;
    }

    //Return JSONObject with name and boolean isHost
    private JSONObject getBasePayload() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(PayloadConfig.name, this.name);
        object.put(PayloadConfig.isHost, this.isHost);
        return object;
    }

    public JSONObject getLoginPayload() throws JSONException {
        JSONObject object = getBasePayload();
        object.put(PayloadConfig.type, PayloadConfig.login);
        return object;
    }

    //Return list of players
    public JSONObject getPlayerListPayload(ArrayList<String> playerNames) throws JSONException {
        JSONObject object = getBasePayload();
        object.put(PayloadConfig.players, playerNames);
        object.put(PayloadConfig.type, PayloadConfig.players);
        return object;
    }

    //Return object with vote
    public JSONObject getVotePayload(String vote) throws JSONException {
        JSONObject object = getBasePayload();
        object.put(PayloadConfig.vote, vote);
        object.put(PayloadConfig.type, PayloadConfig.vote);
        return object;
    }

    //Return object with leave
    public JSONObject getLeavePayload() throws JSONException {
        JSONObject object = getBasePayload();
        object.put(PayloadConfig.leave, true);
        object.put(PayloadConfig.type, PayloadConfig.leave);
        return object;
    }

    //Return object with question
    public JSONObject getQuestionPayload(String question) throws JSONException {
        JSONObject object = getBasePayload();
        object.put(PayloadConfig.question, question);
        object.put(PayloadConfig.type, PayloadConfig.question);
        return object;
    }

    //Return object with result
    public JSONObject getResultPayload(String result) throws JSONException {
        JSONObject object = getBasePayload();
        object.put(PayloadConfig.result, result);
        object.put(PayloadConfig.type, PayloadConfig.result);
        return object;
    }
}
