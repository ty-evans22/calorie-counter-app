package com.example.exp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT_TO_SEND="com.example.exp5.TEXT_TO_SEND";
    private Button button;
    private EditText editText;
    private String textToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextPersonName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSend= editText.getText().toString();
                goToNextScreen();
            }
        });
    }

    private void goToNextScreen() {
        Intent intent = new Intent(this, secondActivity.class);
        intent.putExtra(TEXT_TO_SEND, textToSend);
        startActivity(intent);
    }
}