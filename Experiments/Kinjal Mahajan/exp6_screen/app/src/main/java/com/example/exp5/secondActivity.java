package com.example.exp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String extraText = intent.getStringExtra(MainActivity.TEXT_TO_SEND);
        TextView textView= findViewById((R.id.textView2));
        textView.setText(extraText);
    }
}