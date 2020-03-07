package com.example.rankingyourfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Questions extends AppCompatActivity {

    private TextView questionTextView;
    private ArrayList<String> questions;
    private Button nextButton;
    private Button previousButton;
    private static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        this.questionTextView = findViewById(R.id.questionTextView);
        this.questions = new ArrayList<>();
        this.questions.addAll(DataContainer.getInstance().getCustomQuestions());
        this.questions.addAll(DataContainer.getInstance().getDefaultQuestions());

        if(this.questions.size() > 0){
            this.questionTextView.setText(this.questions.get(index));
        }

        this.nextButton = findViewById(R.id.nextButton);
        this.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                index %= questions.size();
                questionTextView.setText(questions.get(index));
            }
        });

        this.previousButton = findViewById(R.id.previousButton);
        this.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;

                if(index < 0){
                    index = questions.size() - 1;
                }
                questionTextView.setText(questions.get(index));
            }
        });
    }
}
