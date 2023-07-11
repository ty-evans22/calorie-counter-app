package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
/**
 * @author Jacob Burns
 */

/**
 *  Home Page:
 *
 *  Class for creating recipes from a list of ingredients. Taking in a name, description, and ingredients.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Button that takes you to the Ingredient Adder
     */
    ImageButton b1;
    /**
     * Button that takes you to the Recipes Adder
     */
    ImageButton b4;
    /**
     * Button that takes you to the list of recipes
     */
    ImageButton b2;
    private ProgressBar progressbar;
    Button b5;
    Button b6;
    @Override
    /**
     * Creates usable screen using an xml file
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int dailyCals = 55;

        progressbar = findViewById(R.id.calorieProgress);
        progressbar.setProgress(dailyCals);


        b1 = (ImageButton) findViewById(R.id.leaderboardbutton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(MainActivity.this, Leaderboard.class);
                startActivity(i);
            }
        });

        b4 = (ImageButton) findViewById(R.id.recipebutton);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ViewRecipes.class);
                startActivity(i);
            }
        });

        b2 = (ImageButton) findViewById(R.id.imageButton7);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {Intent i = new Intent(MainActivity.this, Chatroom.class)
                    ;startActivity(i);
            }
        });
    }
}