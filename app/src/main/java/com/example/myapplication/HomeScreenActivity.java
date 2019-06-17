package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class           HomeScreenActivity extends AppCompatActivity {

    TextView tellStory;
    TextView listenStory;
    ImageView profile, tellstoryBtn, listenstoryBtn, notification;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mAuth = FirebaseAuth.getInstance();

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        tellStory = findViewById(R.id.tellStory);
        tellStory.setTypeface(typeface);

        tellstoryBtn = findViewById(R.id.tellstoryBtn);

        listenStory = findViewById(R.id.listenStory);
        listenStory.setTypeface(typeface);

        listenstoryBtn = findViewById(R.id.listenstoryBtn);

        // setOnClickListener
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        notification = findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                intent.putExtra("GO", false);
                startActivity(intent);
            }
        });


        tellstoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryFormActivity.class);
                startActivity(intent);
            }
        });

        listenstoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryFormActivity.class);
                startActivity(intent);
            }
        });
    }

    public void logoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
