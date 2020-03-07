package com.example.rankingyourfriends;

import java.util.ArrayList;

public class DataContainer {
    private ArrayList<String> questions;

    private static final DataContainer INSTANCE = new DataContainer();

    private DataContainer(){
        this.questions = new ArrayList<>();
    }

    public static DataContainer getInstance() {
        return INSTANCE;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public void AddQuestion(String question){
        this.questions.add(question);
    }
}
