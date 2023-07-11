package coms309.backend.roundtrip.controllers;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.Recipe;
import coms309.backend.roundtrip.models.Workout;
import coms309.backend.roundtrip.service.ActorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "LeaderboardController")
@RestController
public class LeaderboardController {
    @Autowired
    ActorService actorService;

    // GET

    // get list of all users in ascending order of calories intake - calories burned
    @GetMapping(path = "/leaderboard")
    List<Actor> getLeaderboards() {
        List<Actor> actors = actorService.getAllActors();

        // calculate calories intake, burned, and difference for each actor
        for (Actor actor : actors) {
            actor.setCaloriesBurned(getCaloriesBurned(actor));
            actor.setCaloriesIntake(getCaloriesIntake(actor));
            actor.setCalorieDifference(actor.getCaloriesIntake() -
                    actor.getCaloriesBurned());
        }

        // note: does not save the values to the actors in the db - potentially do that here?

        // sort list of actors in ascending order of calorie difference
        mergeSort(actors, 0, actors.size() - 1);

        return actors;
    }

    // HELPER METHODS

    /** merge function for mergesort algorithm used on actor list **/
    void merge(List<Actor> actors, int left, int middle, int right) {
        int low = middle - left + 1; // size of left subarray
        int high = right - middle;   // size of right subarray

        // create the left and right subarray
        List<Actor> L = new ArrayList<>();
        List<Actor> R = new ArrayList<>();

        int i, j;

        // copy elements into the subarrays
        for (i = 0; i < low; i++) {
            L.add(actors.get(left + i));
        }
        for (j = 0; j < high; j++) {
            R.add(actors.get(middle + 1 + j));
        }

        int k = left; // get starting index for sort
        i = 0; // reset loop variables
        j = 0;

        // merge the left and right subarrays
        while (i < low && j < high) {
            if (L.get(i).getCalorieDifference() <= R.get(j).getCalorieDifference()) {
                actors.set(k, L.get(i));
                i++;
            } else {
                actors.set(k, R.get(j));
                j++;
            }
            k++;
        }

        // merge the remaining elements from the left subarray
        while (i < low) {
            actors.set(k, L.get(i));
            i++;
            k++;
        }

        // merge the remaining elements from the right subarray
        while (j < high) {
            actors.set(k, R.get(j));
            j++;
            k++;
        }
    }

    /** uses merge sort algorithm to sort list of actors **/
    void mergeSort(List<Actor> actors, int left, int right) {
        int middle;
        if (left < right) { // sort only if the left index is less than the right index
            middle = (left + right) / 2;
            mergeSort(actors, left, middle);            // left subarray
            mergeSort(actors, middle + 1, right);   // right subarray
            merge(actors, left, middle, right);         // merge the two subarrays
        }
    }

    /** calculate the total calories burned for an actor **/
    double getCaloriesBurned(Actor actor) {
        List<Workout> workouts = actor.getAllWorkouts();
        double cals = 0;
        for (Workout w : workouts) {
            cals += w.getCaloriesBurned();
        }
        return cals;
    }

    /** calculate the total caloric intake for an actor */
    double getCaloriesIntake(Actor actor) {
        List<Recipe> recipes = actor.getAllRecipes();
        double cals = 0;
        for (Recipe r : recipes) {
            cals += r.getTotalCal();
        }
        return cals;
    }
}
