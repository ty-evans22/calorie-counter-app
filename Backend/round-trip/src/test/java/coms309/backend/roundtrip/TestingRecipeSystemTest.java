package coms309.backend.roundtrip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import coms309.backend.roundtrip.models.*;
import coms309.backend.roundtrip.repositories.ActorRepository;
import coms309.backend.roundtrip.vo.LoginVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

@RunWith(SpringRunner.class)
public class TestingRecipeSystemTest {

    int port = 8080;
    static int aid;
    String email = "test@backend.com", password = "test";

    @Autowired
    ActorRepository actorRepository;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost"; // "coms-309-056.class.las.iastate.edu";

        // create test actor if it doesn't exist
        RestAssured.given()
                .contentType("application/json")
                .body(new Actor("Backend", "Recipes", "Test",
                        "backend", email, password, 21, 75, 190, Role.ADMIN))
                .when()
                .post("/actors/registerActor");

        // verify the test actor exists and store its id
        Response get_actor = RestAssured.given()
                .contentType("application/json")
                .body(new LoginVO(email, password))
                .when()
                .get("/actors/getActor");
        String returnString = get_actor.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(returnString);
            aid = returnObj.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // clear all actor workouts
        RestAssured.given()
                .contentType("application/json")
                .pathParam("aid", aid)
                .when()
                .delete("/actors/{aid}/workouts/all");

        // clear all actor recipes
        RestAssured.given()
                .contentType("application/json")
                .pathParam("aid", aid)
                .when()
                .delete("/recipes/actor/{aid}");
    }

    @Test
    public void workoutTest() throws JSONException {
        /********POST Test********/
        // Send request and receive response
        Response post_response = RestAssured.given()
                .contentType("application/json")
                .pathParam("id", aid)
                .body(new Workout("Running_6mph", 10))
                .when()
                .post("/actors/{id}/workouts/add");


        // Check status code
        int statusCode = post_response.getStatusCode();
        assertEquals(200, statusCode);

        // Check GET actor
        Response get_actor = RestAssured.given()
                .contentType("application/json")
                .body(new LoginVO(email, password))
                .when()
                .get("/actors/getActor");
        String returnString = get_actor.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(returnString);
            JSONArray workoutArr = returnObj.getJSONArray("allWorkouts");
            JSONObject workout = workoutArr.getJSONObject(0);
            assertEquals(10, workout.get("duration"));
            assertEquals(148.11d, workout.get("caloriesBurned"));
            assertEquals("Running_6mph", workout.get("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /********PUT Test********/
        // Send request and receive response
        Response put_response = RestAssured.given()
                .contentType("application/json")
                .pathParam("id", aid)
                .pathParam("index", 1)
                .body(new Workout("Tennis_S", 30))
                .when()
                .put("/actors/{id}/workouts/{index}");


        // Check status code
        int put_statusCode = put_response.getStatusCode();
        assertEquals(200, put_statusCode);

        // Check GET actor
        Response put_get_actor = RestAssured.given()
                .contentType("application/json")
                .body(new LoginVO(email, password))
                .when()
                .get("/actors/getActor");
        String put_returnString = put_get_actor.getBody().asString();
        try {
            JSONObject put_returnObj = new JSONObject(put_returnString);
            JSONArray put_workoutArr = put_returnObj.getJSONArray("allWorkouts");
            JSONObject put_workout = put_workoutArr.getJSONObject(0);
            assertEquals(30, put_workout.get("duration"));
            assertEquals(362.72d, put_workout.get("caloriesBurned"));
            assertEquals("Tennis_S", put_workout.get("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void recipeTest() throws JSONException {
        // declare recipe object to use for test
        Recipe recipe = new Recipe("Cheeseburger",
                "A beef patty with a slice of cheese in between two buns.");

        /********POST Test********/
        // Send request and receive response
        Response post_response = RestAssured.given()
                .contentType("application/json")
                .pathParam("aid", aid)
                .body(recipe)
                .when()
                .post("/recipes/add/{aid}");

        // Check status code
        int statusCode = post_response.getStatusCode();
        assertEquals(200, statusCode);

        Actor actor = actorRepository.findById(aid);

        // Check GET actor
        Response get_actor = RestAssured.given()
                .contentType("application/json")
                .body(new LoginVO(email, password))
                .when()
                .get("/actors/getActor");
        String returnString = get_actor.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(returnString);
            JSONArray recipeArr = returnObj.getJSONArray("allRecipes");
            JSONObject rec = recipeArr.getJSONObject(0);
            assertEquals("Cheeseburger", rec.get("recipeName"));
            assertEquals("A beef patty with a slice of cheese in between two buns.",
                    rec.get("description"));
            assertEquals(0, rec.get("totalCal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /********PUT Test********/
        // Send request and receive response
        Response put_response = RestAssured.given()
                .contentType("application/json")
                .pathParam("id", recipe.getId())
                .body(new Recipe("Salad", "A healthy mix of vegetables."))
                .when()
                .put("/recipes/{id}");

        // Check status code
        int put_statusCode = put_response.getStatusCode();
        assertEquals(200, put_statusCode);

        // Check GET actor
        Response put_get_actor = RestAssured.given()
                .contentType("application/json")
                .body(new LoginVO(email, password))
                .when()
                .get("/actors/getActor");
        String put_returnString = put_get_actor.getBody().asString();
        try {
            JSONObject returnObj = new JSONObject(put_returnString);
            JSONArray recipeArr = returnObj.getJSONArray("allRecipes");
            JSONObject rec = recipeArr.getJSONObject(0);
            assertEquals("Salad", rec.get("recipeName"));
            assertEquals("A healthy mix of vegetables.", rec.get("description"));
            assertEquals(0, rec.get("totalCal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ingredientTest() throws JSONException {
        // create Ingredient object
        Ingredient ingredient = new Ingredient("Ground Beef", 332);

        /********POST Test********/
        // Send request and receive response
        Response post_response = RestAssured.given()
                .contentType("application/json")
                .body(ingredient)
                .when()
                .post("/ingredients/add");

        // Check status code
        int statusCode = post_response.getStatusCode();
        assertEquals(200, statusCode);

        // Check GET ingredient by id
        Response get_ingredients = RestAssured.given()
                .contentType("application/json")
                .pathParam("id", ingredient.getId())
                .when()
                .get("/ingredients/{id}");
        String returnString = get_ingredients.getBody().asString();
        try {
            JSONObject ing = new JSONObject(returnString);
            assertEquals("Ground Beef", ing.get("ingredientName"));
            assertEquals(332, ing.get("calsPer100Gram"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /********PUT Test********/
        // Send request and receive response
        Response put_response = RestAssured.given()
                .contentType("application/json")
                .pathParam("id", ingredient.getId())
                .body(new Ingredient("Cheese", 50))
                .when()
                .put("/ingredients/{id}");

        // Check status code
        int put_statusCode = put_response.getStatusCode();
        assertEquals(200, put_statusCode);

        // Check GET ingredient by id
        Response put_get_ingredient = RestAssured.given()
                .contentType("application/json")
                .pathParam("id", ingredient.getId())
                .when()
                .get("/ingredients/{id}");
        String put_returnString = put_get_ingredient.getBody().asString();
        try {
            JSONObject put_ing = new JSONObject(put_returnString);
            assertEquals("Cheese", put_ing.get("ingredientName"));
            assertEquals(50, put_ing.get("calsPer100Gram"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void recipeIngredientTest() throws JSONException {
//        /********POST Test********/
//        // Send request and receive response
//        Response post_response = RestAssured.given()
//                .contentType("application/json")
//                .body(new RecipeIngredients())
//                .when()
//                .post("/recipe_ingredients/add");
//    }

    // test adding the ingredient to the recipe

    // verify that we can retrieve ingredients for recipes and vice versa

    // test that the leaderboard values are updated based on recipes and workouts added to an actor
}
