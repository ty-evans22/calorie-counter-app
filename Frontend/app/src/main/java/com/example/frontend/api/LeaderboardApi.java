package com.example.frontend.api;

import com.example.frontend.roundtrip.Actors;
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
 * Leaderboard API calls
 */
public interface LeaderboardApi{
    @GET("/leaderboard")
    Call <List<Actors>> getLeaderboard();
    @GET("/dailycalorie")
    Call <Integer> getCalories();
}