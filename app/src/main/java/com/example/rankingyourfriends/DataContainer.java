package com.example.rankingyourfriends;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataContainer {
    private String defaultArrayName, customArrayName;
    private ArrayList<String> defaultQuestions;
    private ArrayList<String> customQuestions;
    private JSONObject obj;

    private static final DataContainer INSTANCE = new DataContainer();

    private DataContainer(){

    }

    public static DataContainer getInstance() {
        return INSTANCE;
    }

    public ArrayList<String> getDefaultQuestions() {
        return defaultQuestions;
    }

    public ArrayList<String> getCustomQuestions() {
        return customQuestions;
    }

    public void addQuestion(String question) throws JSONException {
        this.customQuestions.add(question);
        this.obj.getJSONArray(customArrayName).put(question);
    }

    public void removeQuestion(int pos) throws JSONException {
        this.customQuestions.remove(pos);
        this.obj.getJSONArray(customArrayName).remove(pos);
    }

    public void init(String defaultArrayName, String customArrayName){
        this.defaultArrayName = defaultArrayName;
        this.customArrayName = customArrayName;
    }

    //Returns the JSON string with default questions
    public String getBaseJSON(){
        JSONObject obj = new JSONObject();

        JSONArray defaultArray;
        defaultArray = new JSONArray();

        for (String s: HardCodedQuestions.getQuestions()) {
            defaultArray.put(s);
        }

        try {
            obj.put(defaultArrayName, defaultArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray customArray = new JSONArray();
        try{
            obj.put(customArrayName, customArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }

    //Adds the default and custom questions to the DataContainer
    private void JSONtoArrays(){
        this.customQuestions = new ArrayList<>();
        this.defaultQuestions = new ArrayList<>();

        try {
            JSONArray array = obj.getJSONArray(defaultArrayName);

            for (int i = 0; i <  array.length(); i++){
                this.defaultQuestions.add(array.getString(i));
            }
            array = obj.getJSONArray(customArrayName);

            for (int i = 0; i < array.length(); i++){
                this.customQuestions.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(String obj) {
        try {
            this.obj = new JSONObject(obj);
            JSONtoArrays();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
