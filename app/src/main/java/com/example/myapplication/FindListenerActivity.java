package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FindListenerActivity extends AppCompatActivity {

    ImageView layer_2, layer_3;

    Animation layer2, layer3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_listener);



        layer_2 = (ImageView) findViewById(R.id.layer_2);
        layer_3 = (ImageView) findViewById(R.id.layer_3);


        layer2 = AnimationUtils.loadAnimation(this, R.anim.layer2);
        layer_2.startAnimation(layer2);

        layer3 = AnimationUtils.loadAnimation(this, R.anim.layer3);
        layer_3.startAnimation(layer3);

    }
}
