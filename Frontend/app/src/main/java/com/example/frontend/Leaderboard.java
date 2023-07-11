package com.example.frontend;

import static com.example.frontend.api.ApiClientFactory.GetLeaderboardApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.frontend.api.SlimCallBack;
import com.example.frontend.roundtrip.Actors;

import java.util.List;

public class Leaderboard extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        b = (Button) findViewById(R.id.backhome);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Leaderboard.this,MainActivity.class);
                startActivity(i);
            }
        });

        TextView apiText1 = findViewById(R.id.textView16);
        apiText1.setMovementMethod(new ScrollingMovementMethod());
        apiText1.setHeight(350);

        GetLeaderboardApi().getLeaderboard().enqueue(new SlimCallBack<List<Actors>>(leaderboards->{
            apiText1.setText("");

            for (int i = leaderboards.size()-1; i>= 0; i--){
                apiText1.append(leaderboards.get(i).printabley());
            }
        }, "GetLeaderboard"));

        TextView apiText2 = findViewById(R.id.textView11);
        apiText2.setMovementMethod(new ScrollingMovementMethod());
        apiText2.setHeight(350);

        GetLeaderboardApi().getLeaderboard().enqueue(new SlimCallBack<List<Actors>>(leaderboards->{
            apiText2.setText("");

            for (int i = leaderboards.size()-1; i>= 0; i--){
                apiText2.append(leaderboards.get(i).printablei());
            }
        }, "GetLeaderboard"));


    }
}