package com.example.rankingyourfriends.Logic;

public class GameManager {

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


}
