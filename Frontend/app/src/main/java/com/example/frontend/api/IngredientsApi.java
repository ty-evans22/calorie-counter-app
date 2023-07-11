package com.example.frontend.api;

import com.example.frontend.roundtrip.Ingredients;

import java.lang.ref.Reference;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Jacob Burns
 */

/**
 * Ingredients API calls
 */
public interface IngredientsApi{
    @GET("/ingredients")
    Call <List<Ingredients>> getIngredients1();

    @POST("/ingredients/add/{name}/{cals}")
    Call<Ingredients> PostIngredients(@Path("name") String iName, @Path("cals") Integer cal);


}