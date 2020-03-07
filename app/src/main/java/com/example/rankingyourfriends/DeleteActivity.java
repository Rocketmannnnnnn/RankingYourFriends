package com.example.rankingyourfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class DeleteActivity extends AppCompatActivity {

    private TextView deleteText;
    private Button deleteButton;
    private int toDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        this.deleteText = findViewById(R.id.deleteTextView);
        this.toDelete = (int) getIntent().getSerializableExtra("DELETE");
        this.deleteText.setText(getResources().getString(R.string.DeleteText) + DataContainer.getInstance().getCustomQuestions().get(toDelete));

        this.deleteButton = findViewById(R.id.confirmButton);
        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataContainer dc = DataContainer.getInstance();
                try {
                    dc.removeQuestion(toDelete);

                    SharedPreferences perfs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.QUESTIONS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = perfs.edit();
                    editor.putString(getResources().getString(R.string.QUESTIONS), dc.getObj().toString());

                    if(editor.commit()){
                        Log.i("DELETE", "Saved succesfull");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getBaseContext(), getResources().getString(R.string.DeleteSuccesfull), Toast.LENGTH_LONG).show();
            }
        });
    }
}
