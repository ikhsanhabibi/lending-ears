package com.example.myapplication.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.AllUsersViewHolder>{

    Activity context;
    ArrayList<FirebaseUser> userArrayList;


    public AllUsersAdapter(Activity context, ArrayList<FirebaseUser> userArrayList){
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public AllUsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_users, parent,false);
        AllUsersViewHolder allUsersAdapter = new AllUsersViewHolder(view);

        return allUsersAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull AllUsersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AllUsersViewHolder extends RecyclerView.ViewHolder{

        public AllUsersViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
