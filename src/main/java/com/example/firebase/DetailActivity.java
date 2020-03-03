package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firebase.Storage.FireBaseRepo;

public class DetailActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    int row;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView)findViewById(R.id.textViewDetails);
        editText = (EditText)findViewById(R.id.editTextDetails);

        row = getIntent().getIntExtra(MainActivity.indexKey, 0);

        textView.setText(FireBaseRepo.getNote(row).getHead());
        editText.setText(FireBaseRepo.getNote(row).getBody());



    }

    @Override
    protected void onPause() {
        super.onPause();
        FireBaseRepo.editNote(row, textView.getText().toString(), editText.getText().toString());

    }
}
