package com.example.myapplication.Adapters;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.VoiceCallActivity;
import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.AllUsersViewHolder> {

    Activity context;
    ArrayList<User> userArrayList;
    ImageView profileImageView;

    public AllUsersAdapter(Activity context, ArrayList<User> userArrayList){
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
        User user = userArrayList.get(position);
        holder.textViewName.setText(user.getName());
        holder.textViewCategory.setText(user.getCategory());
        holder.textViewDescription.setText(user.getDescription());
        Glide.with(context).load(user.getProfileimageUrl()).into(profileImageView);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class AllUsersViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName, textViewCategory, textViewDescription;
        Button button;
        public AllUsersViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView)itemView.findViewById(R.id.itemName);
            textViewCategory = (TextView)itemView.findViewById(R.id.itemCategory);
            textViewDescription = (TextView)itemView.findViewById(R.id.itemDescription);
            profileImageView = (ImageView)itemView.findViewById(R.id.profileImage);

            button = itemView.findViewById(R.id.callButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = userArrayList.get(getAdapterPosition());
                    ((VoiceCallActivity)context).callUser(user);
                }
            });
        }
    }
}
