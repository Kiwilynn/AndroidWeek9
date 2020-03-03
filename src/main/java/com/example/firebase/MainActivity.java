package com.example.firebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebase.Model.Note;
import com.example.firebase.Storage.FireBaseRepo;
import com.example.firebase.adapter.RecycleViewAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static com.example.firebase.Storage.FireBaseRepo.notes;
import static com.example.firebase.Storage.FireBaseRepo.notesPath;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //private final static String notes = "notes";
    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecycleViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    public static final String indexKey = "INDEX_KEY";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter();
        recyclerView.setAdapter(adapter);
        FireBaseRepo.adapter = adapter;

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void addNotes(View view) {
        editText = (EditText) findViewById(R.id.newNoteEditText);
            FireBaseRepo.createNote(editText.getText().toString());

    }




   /* private void addNewNote(){
        DocumentReference docRef = FireBaseRepo.db.collection(notesPath).document();
        Map<String, String> map = new HashMap<>();
        map.put("head", "new headline 2");
        map.put("body", "new body 2");
        docRef.set(map);
    }*/
}
