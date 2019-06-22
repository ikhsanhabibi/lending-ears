package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.tts.Voice;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.TimeZone;

public class CategoryFormActivity extends AppCompatActivity {

    Button next;

    private FirebaseAuth mAuth;

    private DatabaseReference mUserStories, mUserDatabase;

    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    MaterialBetterSpinner betterSpinner;

    EditText mDescription;

    String userId, category, description, username;

    String [] SPINNERLIST = {"Relationship", "Education", "Sports", "Family Life", "Social Issues", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_form);

        // Dropdown list
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        betterSpinner = (MaterialBetterSpinner) findViewById(R.id.categorylist);
        betterSpinner.setAdapter(arrayAdapter);

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        next = findViewById(R.id.next);
        next.setTypeface(typeface);

        mDescription = findViewById(R.id.storydescription);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mUserStories = FirebaseDatabase.getInstance().getReference().child("Stories").child(userId);
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitStories();
                Intent intent = new Intent(CategoryFormActivity.this, VoiceCallActivity.class);
                intent.putExtra("findlistener", R.layout.activity_find_listener);
                startActivity(intent);
            }
        });
    }



    private void submitStories(){

        description = mDescription.getText().toString();
        category = betterSpinner.getText().toString();

        final Map userStories = new HashMap();

        mUserDatabase.child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue(String.class);
                userStories.put("username", username);
                mUserStories.updateChildren(userStories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userStories.put("category", category);
        userStories.put("description", description);
        userStories.put("userid", userId);

        mUserStories.updateChildren(userStories);
    }
}
