package com.example.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private Button btn_continue;
    private TextView signUp;
    private Button btn_signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setTypeface(typeface);

        signUp = findViewById(R.id.signUp);
        signUp.setTypeface(typeface);

        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signIn.setTypeface(typeface);


    }


}
