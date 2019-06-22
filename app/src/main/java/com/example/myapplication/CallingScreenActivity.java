package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;

public class CallingScreenActivity extends AppCompatActivity {


    private Chronometer chronometer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_screen);

        chronometer2 = findViewById(R.id.chronometer2);
        chronometer2.start();


    }
}
