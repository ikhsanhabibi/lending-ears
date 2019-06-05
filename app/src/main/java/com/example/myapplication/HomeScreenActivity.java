package com.example.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {

    TextView tellStory;
    TextView listenStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        tellStory = findViewById(R.id.tellStory);
        tellStory.setTypeface(typeface);

        listenStory = findViewById(R.id.listenStory);
        listenStory.setTypeface(typeface);

    }
}
