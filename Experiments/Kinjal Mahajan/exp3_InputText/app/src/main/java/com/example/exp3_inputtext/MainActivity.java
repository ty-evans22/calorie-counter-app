package com.example.exp3_inputtext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView=(TextView) findViewById(R.id.textView);
        inputText=(EditText) findViewById(R.id.inputText);
    }

    public void updateText(View view){

        textView.setText("Hi"+inputText.getText());
        System.out.println("Button Clicked");
    }
}