package com.example.rankingyourfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }
}
