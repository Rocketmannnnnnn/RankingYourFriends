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

    private static final DataContainer INSTANCE = new DataContainer();

    private DataContainer(){
        this.customQuestions = new ArrayList<>();
        this.defaultQuestions = new ArrayList<>();
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

    public void AddQuestion(String question){
        this.customQuestions.add(question);
    }

    public void init(String defaultArrayName, String customArrayName){
        this.defaultArrayName = defaultArrayName;
        this.customArrayName = customArrayName;
    }

    //Returns the JSON string with default questions
    public String getDefaultString(){
        JSONObject obj = new JSONObject();

        JSONArray defaultArray;
        defaultArray = new JSONArray();
        defaultArray.put("Op wiens kinderen zou je absoluut niet willen letten?");

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
    public void fillData(String sharedPerfs){
        try {
            JSONObject obj = new JSONObject(sharedPerfs);
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
}
