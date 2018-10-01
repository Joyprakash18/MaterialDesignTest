package com.sayan.sample.materialdesigntest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InflaterTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater_test);
        View linearLayout = findViewById(R.id.linearLayout);
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i <= 10; i++)
        layoutInflater.inflate(R.layout.child_inflater_text, (ViewGroup) linearLayout, true);
        layoutInflater.inflate(R.layout.child_inflater_button, (ViewGroup) linearLayout, true);
    }
}
