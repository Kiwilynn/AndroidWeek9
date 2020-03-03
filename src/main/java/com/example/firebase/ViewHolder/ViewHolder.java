package com.example.firebase.ViewHolder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.DetailActivity;
import com.example.firebase.MainActivity;
import com.example.firebase.R;
import com.example.firebase.Storage.FireBaseRepo;

public class ViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "ViewHolder";
    int rowNr = 0;
    TextView textView;
    Intent intent;



    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textViewSimpleRow);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra(MainActivity.indexKey, rowNr);
                v.getContext().startActivity(intent);

            }
        });

        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "onLongClick: ");
                FireBaseRepo.deleteNote(rowNr);
                return false;
            }
        });
    }

    public void setPosition(int position) {
        rowNr = position;
        textView.setText(FireBaseRepo.notes.get(position).getHead());
    }
}
