package com.example.frontend;

import static com.example.frontend.api.ApiClientFactory.GetRecipesApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.frontend.api.SlimCallBack;
import com.example.frontend.roundtrip.PRecipes;
import com.example.frontend.roundtrip.RecipeIngredients;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Jacob Burns
 */

/**
 * Recipes:
 *
 *  Class for creating recipes from a list of ingredients. Taking in a name, description, and ingredients.
 */
public class Recipes extends AppCompatActivity {
    /**
     * Button to the view recipes screen
     */
    Button b;

    @Override
    /**
     * Creates usable screen using an xml file
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        //int cals = 0;
        Intent i = getIntent();
        List<String> ingredients = i.getStringArrayListExtra("ingredients");
        //String currentrecipe = i.getStringExtra("recipe");
        //ArrayList<Integer> amount = new ArrayList<>();
        //List<RecipeIngredients> recipeIngredients = new ArrayList<>();


        TextView apiText1 = findViewById(R.id.textView5);
        apiText1.setMovementMethod(new ScrollingMovementMethod());
        apiText1.setHeight(350);

        apiText1.setText("");

        for (int j = ingredients.size() - 1; j >= 0; j--) {
            apiText1.append(ingredients.get(j));
            apiText1.append("\n");
        }

       // EditText i1 = findViewById(R.id.editTextTextPersonName8);
        //EditText i2 = findViewById(R.id.editTextTextPersonName9);
        //EditText i3 = findViewById(R.id.editTextTextPersonName10);
        //EditText i4 = findViewById(R.id.editTextTextPersonName11);
        //EditText i5 = findViewById(R.id.editTextTextPersonName12);
        //EditText i6 = findViewById(R.id.editTextTextPersonName13);

       // int i1f = Integer.parseInt(i1.getText().toString());
        //int i2f = Integer.parseInt(i1.getText().toString());
        //int i3f = Integer.parseInt(i1.getText().toString());
        //int i4f = Integer.parseInt(i1.getText().toString());
        //int i5f = Integer.parseInt(i1.getText().toString());
        //int i6f = Integer.parseInt(i1.getText().toString());

      //  amount.add(i1f);
        //amount.add(i2f);
        //amount.add(i3f);
        //amount.add(i4f);
        //amount.add(i5f);
        //amount.add(i6f);

        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Recipes.this, ViewRecipes.class);
                startActivity(i);
            }
        });

        b = (Button) findViewById(R.id.back2adder);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Recipes.this, PersonalRecipes.class);
                startActivity(i);
            }
        });


                // for(int j = ingredients.size()-1; j>=0; j--){
                //    recipeIngredients.add(new RecipeIngredients(ingredients.get(j),currentrecipe, amount.get(j)));//       /}
                //     GetRecipesApi().PostRingredients().enqueue(new SlimCallBack<PRecipes>(recipes->{
    }
}

