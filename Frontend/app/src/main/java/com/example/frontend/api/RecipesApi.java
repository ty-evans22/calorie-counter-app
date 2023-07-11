package com.example.frontend.api;

import com.example.frontend.roundtrip.Ingredients;
import com.example.frontend.roundtrip.PRecipes;
import com.example.frontend.roundtrip.RecipeIngredients;

import java.lang.ref.Reference;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Jacob Burns
 */

/**
 * Recipes API calls
 */
public interface RecipesApi {
    @GET("/recipe_ingredients/recipe/ingredients")
    Call<List<Ingredients>> getIngredients2();

    @GET("/recipes")
    Call<List<PRecipes>> getPRecipes();

    @POST("/recipes/add/{name}/{desc}/{cals}/{userId}")
    Call<PRecipes> PostRecipeswID(@Path("name") String rname, @Path("desc") String rdescription, @Path("cals") String rcalories, @Path("userId") int userId);

    @POST("/recipes/add/{name}/{desc}/{cals}")
    Call<PRecipes> PostRecipes(@Path("name") String rname, @Path("desc") String rdescription, @Path("cals") int rcalories);

   // @POST("/recipes/add")

    @POST("/recipes_ingredients/add")
    Call<PRecipes> PostRingredients(@Path("recipeIngredients") RecipeIngredients recipeIngredients);

}
