package com.example.frontend.roundtrip;

import android.widget.EditText;

public class PRecipes {

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return description;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.description = recipeDescription;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Recipe Name
     */
    private String recipeName;
    /**
     * Recipe Description
     */
    private String description;
    /**
     * Recipe total calories
     */
    private int calories;

    public PRecipes(String recipeName, String recipeDescription, int calories) {

    }

    /**
     * Returns a recipe
     * @return
     */
    public String printable(){
        return
                "\nName: " + this.recipeName +
                        "\nDescription: " + this.description +
                        "\nCalories: " + this.calories + "\n";
    }

}
