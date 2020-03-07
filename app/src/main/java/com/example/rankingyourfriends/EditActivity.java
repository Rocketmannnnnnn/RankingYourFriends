package com.example.rankingyourfriends;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

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
        this.adapter = new CustomQuestionsAdapter(DataContainer.getInstance().getCustomQuestions());
        this.recyclerView = findViewById(R.id.questionsRecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adapter);
        this.addButton = findViewById(R.id.addButton);

        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = newQuestion.getText().toString();
                if(!DataContainer.getInstance().getCustomQuestions().contains(question) && !question.trim().isEmpty()){
                    Save(question);
                    adapter.notifyDataSetChanged();
                    newQuestion.setText("");
                }
            }
        });
    }

    private void Save(String question){
        DataContainer dc = DataContainer.getInstance();
        try {
            dc.addQuestion(question);

            SharedPreferences perfs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.QUESTIONS), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = perfs.edit();
            editor.putString(getResources().getString(R.string.QUESTIONS), dc.getObj().toString());
            Log.i("SAVE", dc.getObj().toString());
            //editor.apply();
            if(editor.commit()){
                Log.i("COMMIT", "Save succesfull");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
