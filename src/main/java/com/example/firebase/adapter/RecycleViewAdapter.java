package com.example.firebase.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.Storage.FireBaseRepo;
import com.example.firebase.ViewHolder.ViewHolder;

public class RecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.simplerow, parent, false);
        return new ViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return FireBaseRepo.notes.size();
    }
}
