package com.uc.week1_0706011910003.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.week1_0706011910003.Model.User;
import com.uc.week1_0706011910003.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<User> mContacts;
    ArrayList<User> saveList;
    private OnNoteListener monNoteListener;

    public MyAdapter(ArrayList<User> mContacts, OnNoteListener onNoteListener) {
        this.mContacts = mContacts;
        this.monNoteListener = onNoteListener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new MyViewHolder(view, monNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mName.setText(mContacts.get(position).getName());
        holder.mAge.setText(mContacts.get(position).getAge());
        holder.mAddress.setText(mContacts.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mName, mAge, mAddress;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            mName = itemView.findViewById(R.id.view_name);
            mAge = itemView.findViewById(R.id.view_age);
            mAddress = itemView.findViewById(R.id.view_address);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        onNoteListener.OnNoteClick(getAdapterPosition());

        }
    }
    public interface OnNoteListener{
        void OnNoteClick (int position);
    }
}
