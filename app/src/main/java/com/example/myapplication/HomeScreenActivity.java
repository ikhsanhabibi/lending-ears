package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {

    TextView tellStory;
    TextView listenStory;
    ImageView profile;

    ImageView tell_your_story_icon;
    ImageView listen_a_story_icon;

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

        // setOnClickListener
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("GO", false);
                startActivity(intent);
                finish();
            }
        });

        tell_your_story_icon = (ImageView) findViewById(R.id.tell_your_story_icon);
        tell_your_story_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoiceCallActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("GO", false);
                startActivity(intent);
                finish();
            }
        });




    }
}
