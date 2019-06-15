package com.example.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class CategoryFormActivity extends AppCompatActivity {

    Button next;

    String [] SPINNERLIST = {"Relationship", "Education", "Sports", "Family Life", "Social Issues", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_form);

        // Dropdown list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.android_material_design_spinner);
        betterSpinner.setAdapter(arrayAdapter);

        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        next = findViewById(R.id.next);
        next.setTypeface(typeface);
    }

}
