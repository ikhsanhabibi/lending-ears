package com.example.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    TextView signIn;
    Button btn_sigUp;
    Button btn_continue;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        signIn = findViewById(R.id.signIn);
        signIn.setTypeface(typeface);

        btn_sigUp = findViewById(R.id.btn_signUp);
        btn_sigUp.setTypeface(typeface);

        btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setTypeface(typeface);

        forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setTypeface(typeface);

    }
}
