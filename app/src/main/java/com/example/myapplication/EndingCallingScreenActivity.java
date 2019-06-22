package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EndingCallingScreenActivity extends AppCompatActivity {

    Button btn_return;
    TextView textViewDateTime, textViewDuration;

    DatabaseReference mCallDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending_calling_screen);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        mCallDatabase = FirebaseDatabase.getInstance().getReference().child("Calls");

        String historyid = getIntent().getExtras().getString("historycallid","default");

        mCallDatabase.child(historyid).child("dateandtime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dateandtime = dataSnapshot.getValue(String.class);
                textViewDateTime = findViewById(R.id.dateandtime);
                textViewDateTime.setText(dateandtime);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mCallDatabase.child(historyid).child("duration").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String duration = dataSnapshot.getValue(String.class);
                textViewDuration = findViewById(R.id.duration);
                textViewDuration.setText(duration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        btn_return = findViewById(R.id.btn_return);
        btn_return.setTypeface(typeface);

        // setOnClickListener
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), HomeScreenActivity.class);
                startActivity(s);
                finish();
            }
        });
    }
}
