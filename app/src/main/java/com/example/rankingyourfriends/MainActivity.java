package com.example.rankingyourfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView jokeTv;
    private Button playButton;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.jokeTv = findViewById(R.id.jokeTextView);
        this.playButton = findViewById(R.id.playButton);
        this.editButton = findViewById(R.id.editButton);

        this.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Questions.class);
                view.getContext().startActivity(i);
            }
        });

        this.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), EditActivity.class);
                view.getContext().startActivity(i);
            }
        });

        initDataContainer();
    }

    private void initDataContainer(){
        DataContainer dc = DataContainer.getInstance();
        dc.init(getResources().getString(R.string.DEFAULTQUESTIONS), getResources().getString(R.string.CUSTOMQUESTIONS));

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String questions = sharedPref.getString("questions", dc.getDefaultString());

        dc.fillData(questions);
    }
}
