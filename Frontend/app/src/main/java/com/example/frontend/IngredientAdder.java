package com.example.frontend;

import static com.example.frontend.api.ApiClientFactory.GetIngredientsApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import com.example.frontend.api.SlimCallBack;
import com.example.frontend.roundtrip.Ingredients;

import java.util.List;
/**
 * @author Jacob Burns
 */

/**
 *  Ingredient Adder:
 *
 *  Class for creating recipes from a list of ingredients. Taking in a name, description, and ingredients.
 */

public class IngredientAdder extends AppCompatActivity {
    /**
     * Button for adding Ingredients
     */
    Button b2;
    /**
     * Button for going back to home
     */
    Button b3;
    @Override
    /**
     * Creates usable screen using an xml file
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_adder);

        b2 = (Button) findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IngredientAdder.this, IngredientRetriever.class);
                startActivity(i);
            }
        });

        Button addIngredient = findViewById(R.id.IngredientAdderBtn);
        EditText iName = findViewById(R.id.editTextiName);
        EditText CalPerG = findViewById(R.id.editTextCalPerG);




        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetIngredientsApi().PostIngredients(iName.getText().toString(), Integer.parseInt(CalPerG.getText().toString())).enqueue(new SlimCallBack<Ingredients>(ingredient->{
                    iName.setText("Ingredient");
                    CalPerG.setText("Added!");
                }));
            }
        });

        b3 = (Button) findViewById(R.id.backfromadder);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IngredientAdder.this, PersonalRecipes.class);
                startActivity(i);
            }
        });

    }
}





