package com.example.frontend.roundtrip;

public class Ingredients{
    /**
     * Ingredient ID
     */
    private int id;
    /**
     * Ingredient Name
     */
    private String ingredientName;
    /**
     * Ingredients Calories per 100 grams
     */
    private int calsPer100Gram;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getCaloriesper100grams() {
        return calsPer100Gram;
    }

    public void setCaloriesper100grams(int caloriesper100grams) {
        this.calsPer100Gram = caloriesper100grams;
    }

    /**
     * Returns an Ingredient
     * @return
     */
    public String printable(){
        return
                "\nID: " + this.id +
                "\nIngredient: " + this.ingredientName +
                "\nCalories Per 100 Grams: " + this.calsPer100Gram + "\n";
    }

    public String iNamePrint(){
        return this.ingredientName;
    }
}