package com.example.frontend;

import static com.example.frontend.api.ApiClientFactory.GetIngredientsApi;
import static com.example.frontend.api.ApiClientFactory.GetRecipesApi;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.api.SlimCallBack;
import com.example.frontend.roundtrip.Ingredients;
import com.example.frontend.roundtrip.PRecipes;

import java.util.List;
/**
 * @author Jacob Burns
 */

/**
 * View Recipes:
 *
 *  Class for creating recipes from a list of ingredients. Taking in a name, description, and ingredients.
 */
public class ViewRecipes extends AppCompatActivity {
    /**
     * Button for going back to home
     */
    Button b,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);



        b = (Button) findViewById(R.id.button3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewRecipes.this,MainActivity.class);
                startActivity(i);
            }
        });

        b2 = (Button) findViewById(R.id.button4);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewRecipes.this,PersonalRecipes.class);
                startActivity(i);
            }
        });

        TextView apiText1 = findViewById(R.id.textView8);
        apiText1.setMovementMethod(new ScrollingMovementMethod());
        apiText1.setHeight(350);

        GetRecipesApi().getPRecipes().enqueue(new SlimCallBack<List<PRecipes>>(recipes->{
            apiText1.setText("");

            for (int i = recipes.size()-1; i>= 0; i--){
                apiText1.append(recipes.get(i).printable());
            }
        }, "GetAllIngredients"));
    }

}

