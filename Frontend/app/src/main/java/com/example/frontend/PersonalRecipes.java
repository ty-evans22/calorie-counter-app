package com.example.frontend;

import static com.example.frontend.api.ApiClientFactory.GetIngredientsApi;
import static com.example.frontend.api.ApiClientFactory.GetRecipesApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.security.identity.PersonalizationData;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.frontend.api.SlimCallBack;
import com.example.frontend.roundtrip.Ingredients;
import com.example.frontend.roundtrip.PRecipes;
import com.example.frontend.roundtrip.RecipeIngredients;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacob Burns
 */

/**
 * Personal Recipes:
 *
 *  Class for creating recipes from a list of ingredients. Taking in a name, description, and ingredients.
 */
public class PersonalRecipes extends AppCompatActivity {

    /**
     * Search bar for searching through ingredients
     */
    SearchView searchView;
    /**
     * Shows list of all ingredients
     */
    ListView listView;
    /**
     * Shows list of all selected ingredients
     */
    ListView listView2;
    /**
     * Adapts list for listView
     */
    ArrayAdapter adapter;
    /**
     * Adapts list for listView2
     */
    ArrayAdapter adapter2;
    /**
     * List of all ingredients selected for addition to a recipe
     */
    List<String> toBeAdded = new ArrayList<>();
    /**
     * List of all ingredients retrieved from database
     */
    List<String> ingredientsl = new ArrayList<>();
    /**
     * Button to add selected ingredients to the recipe
     */
    Button b;
    /**
     * Button to go back to home
     */
    Button b2,b3;

    @Override
    /**
     * Creates usable screen using an xml file
     */
    protected void onCreate(Bundle savedInstanceState) {
        toBeAdded.clear();
        ingredientsl.clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_recipes);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.Ingrelist);
        listView2 = findViewById(R.id.Relist);

        int cals = 0;

        List<PRecipes> recipe = new ArrayList<>();

        GetIngredientsApi().getIngredients1().enqueue(new SlimCallBack<List<Ingredients>>(response ->{

            for (int i = response.size()-1; i>= 0; i--){
                ingredientsl.add((response.get(i)).iNamePrint());
            }
        }));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ingredientsl);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,toBeAdded);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(ingredientsl.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(PersonalRecipes.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        listView.setOnItemClickListener(listClick);
        listView2.setOnItemClickListener(listClick2);

        EditText rName = findViewById(R.id.EnterRName);
        EditText rDescription = findViewById(R.id.EnterD);

        b = (Button) findViewById(R.id.AddtoL);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PersonalRecipes.this, Recipes.class);
                i.putExtra("ingredients", (ArrayList<String>) toBeAdded);
                GetRecipesApi().PostRecipes(rName.getText().toString(), rDescription.getText().toString(), cals).enqueue(new SlimCallBack<PRecipes>(recipes->{
                }));
                //PRecipes recipe = new PRecipes(rName.getText().toString(), rDescription.getText().toString(), cals);
                //i.putExtra("recipe", recipe.toString());
                startActivity(i);
            }
        });

        b2 = (Button) findViewById(R.id.button5);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PersonalRecipes.this, MainActivity.class);
                startActivity(i);
            }
        });

        b3 = (Button) findViewById(R.id.button6);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PersonalRecipes.this, IngredientAdder.class);
                startActivity(i);
            }
        });

    }




    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
            toBeAdded.add(ingredientsl.get(i));

            Toast.makeText(PersonalRecipes.this, "Ingredient Added!",Toast.LENGTH_LONG).show();

            listView2.setAdapter(adapter2);
        }
    };

    private AdapterView.OnItemClickListener listClick2 = new AdapterView.OnItemClickListener () {
        public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
            toBeAdded.remove(toBeAdded.get(i));

            Toast.makeText(PersonalRecipes.this, "Ingredient Removed!",Toast.LENGTH_LONG).show();

            listView.setAdapter(adapter);
            listView2.setAdapter(adapter2);
        }
    };
}