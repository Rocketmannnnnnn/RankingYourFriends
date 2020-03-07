package com.example.rankingyourfriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText newQuestion;
    private Button addButton;
    private RecyclerView recyclerView;
    private CustomQuestionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        this.newQuestion = findViewById(R.id.newQuestionEditText);
        this.adapter = new CustomQuestionsAdapter(DataContainer.getInstance().getQuestions());
        this.recyclerView = findViewById(R.id.questionsRecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adapter);
        this.addButton = findViewById(R.id.addButton);

        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = newQuestion.getText().toString();
                if(!DataContainer.getInstance().getQuestions().contains(question)){
                    DataContainer.getInstance().AddQuestion(question);
                    //TODO add question to SQLite
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
