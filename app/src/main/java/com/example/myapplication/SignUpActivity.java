package com.example.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private Button btn_continue;
    private TextView sign_up;
    private TextView sign_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setTypeface(typeface);

        sign_up = findViewById(R.id.sign_up);
        sign_up.setTypeface(typeface);

        sign_in = findViewById(R.id.sign_in);
        sign_in.setTypeface(typeface);


    }


}
