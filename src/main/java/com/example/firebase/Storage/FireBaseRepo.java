package com.example.firebase.Storage;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Model.Note;
import com.example.firebase.adapter.RecycleViewAdapter;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class FireBaseRepo {
    public static List<Note> notes = new ArrayList<>();
    public static RecyclerView.Adapter adapter;
    public final static String notesPath = "notes";
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "FireBaseRepo";

    public static Note getNote(int index) {
        if (index >= notes.size()) {
            return new Note("", "", "");
        } else {
            return notes.get(index);
        }
    }

    public static void createNote(String headLine){

        DocumentReference documentReference = db.collection(notesPath).document();
        Map<String, String> map = new HashMap<>();
        map.put("head", headLine);
        map.put("body", "filler");
        documentReference.set(map);
    }

    public static void deleteNote(int index){

        String key = notes.get(index).getId();
        DocumentReference documentReference = FireBaseRepo.db.collection(notesPath).document(key);
        documentReference.delete();
    }

    public static void editNote(int index, String head, String body){
        String id = notes.get(index).getId();
        //Get a firebase ref.
        DocumentReference documentReference = FireBaseRepo.db.collection(notesPath).document(id);

        //Make map to store all data
        Map<String, String> map = new HashMap<>();
        map.put("head", head);
        map.put("body", body);
        //Overwrites previous map
        documentReference.set(map);

        notes.get(index).setHead(head);
        notes.get(index).setBody(body);
    }


    static{
        startNoteListener();
    }


    private static void startNoteListener() {
        FireBaseRepo.db.collection(notesPath).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {

                Log.d(TAG, "onEvent: read from FireBase");

                for (DocumentSnapshot snap: values.getDocuments()) {
                    notes.add(new Note(snap.getId(), snap.get("head").toString(), snap.get("body").toString()));


                    Log.d(TAG,   snap.get("head").toString());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

}
