package com.example.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    Button btn_sign_in;
    TextView btn_sign_up;
    Button btn_continue;
    TextView forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_sign_in.setTypeface(typeface);

        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_up.setTypeface(typeface);

        btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setTypeface(typeface);

        forgot_password = findViewById(R.id.forgot_password);
        forgot_password.setTypeface(typeface);

    }
}
