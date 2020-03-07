package com.example.rankingyourfriends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomQuestionsAdapter extends RecyclerView.Adapter<CustomQuestionsAdapter.CQViewHolder> {

    private ArrayList<String> questions;

    public CustomQuestionsAdapter(ArrayList<String> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public CQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        view = inflater.inflate(R.layout.activity_row_item, parent, false);
        return new CQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CQViewHolder holder, int position) {
        final String string = this.questions.get(position);
        holder.questionText.setText(string);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class CQViewHolder extends RecyclerView.ViewHolder {
        public TextView questionText;

        public CQViewHolder(@NonNull View itemView) {
            super(itemView);
            this.questionText = itemView.findViewById(R.id.rowItemText);
            this.questionText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO go to delete activity
                }
            });
        }
    }
}
