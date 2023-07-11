package com.example.frontend.roundtrip;

public class RecipeIngredients {
    /**
     * Ingredient from the ingredients list
     */
    private Ingredients ingredient;
    /**
     * Recipe object
     */
    private PRecipes recipe;
    /**
     * Amount for each ingredient
     */
    private int amount;

    public RecipeIngredients(String s, String currentrecipe, Integer integer) {
    }


    public Ingredients getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredients ingredient) {
        this.ingredient = ingredient;
    }

    public PRecipes getRecipe() {
        return recipe;
    }

    public void setRecipe(PRecipes recipe) {
        this.recipe = recipe;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



}
