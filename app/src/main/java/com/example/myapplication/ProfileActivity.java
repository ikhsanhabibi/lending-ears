package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        profileName = findViewById(R.id.profileName);
        profileName.setTypeface(typeface);

        // setOnClickListener
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                intent.putExtra("GO", false);
                startActivity(intent);
            }
        });

    }
}
