package com.example.frontend;

import static com.example.frontend.api.ApiClientFactory.GetIngredientsApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import com.example.frontend.api.SlimCallBack;
import com.example.frontend.roundtrip.Ingredients;

import java.util.List;
/**
 * @author Jacob Burns
 */

/**
 *  Ingredient Retriever:
 *
 *  Class for creating recipes from a list of ingredients. Taking in a name, description, and ingredients.
 */
public class IngredientRetriever extends AppCompatActivity {
    /**
     * Button for going back to the Ingredient Adder
     */
    Button b3;

    @Override
    /**
     * Creates usable screen using an xml file
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_retriever);

        b3 = (Button) findViewById(R.id.BackToIngredients);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IngredientRetriever.this, IngredientAdder.class);
                startActivity(i);
            }
        });

        TextView apiText1 = findViewById(R.id.textView2);
        apiText1.setMovementMethod(new ScrollingMovementMethod());
        apiText1.setHeight(350);

        GetIngredientsApi().getIngredients1().enqueue(new SlimCallBack<List<Ingredients>>(response ->{
            apiText1.setText("");

            for (int i = response.size()-1; i>= 0; i--){
                apiText1.append(response.get(i).printable());
            }
        }, "GetAllIngredients"));
    }
}