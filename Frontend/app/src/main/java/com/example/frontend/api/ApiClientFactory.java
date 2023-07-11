package com.example.frontend.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @author Jacob Burns
 */

/**
 * API connection class
 */
public class ApiClientFactory {

    static Retrofit apiClientSeed = null;

    static Retrofit GetApiClientSeed() {

        if (apiClientSeed == null) {
            apiClientSeed = new Retrofit.Builder()
                    .baseUrl("http://coms-309-056.class.las.iastate.edu:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiClientSeed;
    }

    public static IngredientsApi GetIngredientsApi(){
        return GetApiClientSeed().create(IngredientsApi.class);
    }

    public static LeaderboardApi GetLeaderboardApi(){
        return GetApiClientSeed().create(LeaderboardApi.class);
    }


    public static RecipesApi GetRecipesApi(){
        return GetApiClientSeed().create(RecipesApi.class);
    }
}