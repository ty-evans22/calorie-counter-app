package coms309.backend.roundtrip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.Role;
import coms309.backend.roundtrip.models.Workout;
import coms309.backend.roundtrip.vo.LoginVO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

@RunWith(SpringRunner.class)
public class TestingSystemTest {

	int port = 8080;
	static int aid;

	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://coms-309-056.class.las.iastate.edu";
		RestAssured.given()
				.contentType("application/json")
				.body(new Actor("RUSHAL'S ACTOR TEST", null, "system", "testsystem", "test@iastate.edu", "test", 
						21, 76, 190, Role.ADMIN))
				.when()
				.post("/actors/registerActor");
		
		Response get_actor = RestAssured.given()
				.contentType("application/json")
				.body(new LoginVO("test@iastate.edu", "test"))
				.when()
				.get("/actors/getActor");
		String returnString = get_actor.getBody().asString();
		try {
			JSONObject returnObj = new JSONObject(returnString);
			aid = returnObj.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void actorTest() throws JSONException {
		/********POST Test********/
		// POST - Send request and receive response
		Response post_response = RestAssured.given()
				.contentType("application/json")
				.body(new Actor("test", null, "system", "testsystem", "test_actor@iastate.edu", "test", 
						21, 76, 190, Role.ADMIN))
				.when()
				.post("/actors/registerActor");


		// Check status code
		int statusCode = post_response.getStatusCode();
		assertEquals(200, statusCode);
		
		// GET actor
		Response get_actor = RestAssured.given()
				.contentType("application/json")
				.body(new LoginVO("test_actor@iastate.edu", "test"))
				.when()
				.get("/actors/getActor");
		String returnString = get_actor.getBody().asString();
//		System.out.println("******************");
//		System.out.println(returnString);
//		System.out.println("******************");
		try {
			JSONObject returnObj = new JSONObject(returnString);
			aid = returnObj.getInt("id");
			assertEquals("test", returnObj.get("firstName"));
			assertEquals(JSONObject.NULL, returnObj.get("middleName"));
			assertEquals("system", returnObj.get("lastName"));
			assertEquals("testsystem", returnObj.get("userName"));
			assertEquals("test_actor@iastate.edu", returnObj.get("email"));
			assertEquals("test", returnObj.get("password"));
			assertEquals(21, returnObj.get("age"));
			assertEquals(76, returnObj.get("heightInches"));
			assertEquals(190d, returnObj.get("weightLbs"));
			assertEquals("ADMIN", returnObj.get("role"));	
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		/********PUT Test********/
		// PUT - Send request and receive response
		Response put_response = RestAssured.given()
				.contentType("application/json")
				.pathParam("age", 70)
				.body(new LoginVO("test_actor@iastate.edu", "test"))
				.when()
				.put("/actors/updateActorAge/{age}");


		// Check status code
		int put_statusCode = put_response.getStatusCode();
		assertEquals(200, put_statusCode);
		
		// GET actor
		Response put_get_actor = RestAssured.given()
				.contentType("application/json")
				.body(new LoginVO("test_actor@iastate.edu", "test"))
				.when()
				.get("/actors/getActor");
		String put_returnString = put_get_actor.getBody().asString();
		try {
			JSONObject put_returnObj = new JSONObject(put_returnString);
			assertEquals(70, put_returnObj.get("age"));	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		/********DELETE Test********/
		// DELETE - Send request and receive response
				Response del_response = RestAssured.given()
						.contentType("application/json")
						.body(new LoginVO("test_actor@iastate.edu", "test"))
						.when()
						.delete("/actors/deleteActor");


				// Check status code
				int del_statusCode = del_response.getStatusCode();
				assertEquals(200, del_statusCode);
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
				.pathParam("id", aid)
				//.body(new LoginVO("test@iastate.edu", "test"))
				.when()
				.get("/actors/{id}/workouts/1");
		String returnString = get_actor.getBody().asString();
		try {
			JSONObject workout = new JSONObject(returnString);
//			JSONObject returnObj = new JSONObject(returnString);
//			JSONArray workoutArr = returnObj.getJSONArray("allWorkouts");
//			JSONObject workout = workoutArr.getJSONObject(0);
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
						.body(new LoginVO("test@iastate.edu", "test"))
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
				
				/********GET all Test********/
				//add another workout
				RestAssured.given()
				.contentType("application/json")
				.pathParam("id", aid)
				.body(new Workout("Walking_3mph", 60))
				.when()
				.post("/actors/{id}/workouts/add");
				
				//add another workout
				RestAssured.given()
				.contentType("application/json")
				.pathParam("id", aid)
				.body(new Workout("Walking_3mph", 90))
				.when()
				.post("/actors/{id}/workouts/add");
				
				// Check GET actor
				Response get_all = RestAssured.given()
						.contentType("application/json")
						.pathParam("id", aid)
						.when()
						.get("/actors/{id}/workouts/all");
				String get_all_returnString = get_all.getBody().asString();
				try {
					JSONArray get_all_workoutArr = new JSONArray(get_all_returnString);
//					JSONObject get_all_returnObj = new JSONObject(get_all_returnString);
//					JSONArray get_all_workoutArr = get_all_returnObj.getJSONArray("allWorkouts");
					assertEquals(3, get_all_workoutArr.length());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				/********DELETE Test********/
				// DELETE ONE - Send request and receive response
				Response del_response_1 = RestAssured.given()
						.contentType("application/json")
						.pathParam("id", aid)
						.when()
						.delete("/actors/{id}/workouts/1");


				// Check status code
				int del_statusCode_1 = del_response_1.getStatusCode();
				assertEquals(200, del_statusCode_1);
				
				// DELETE ALL- Send request and receive response
				Response del_response = RestAssured.given()
						.contentType("application/json")
						.pathParam("id", aid)
						.when()
						.delete("/actors/{id}/workouts/all");


				// Check status code
				int del_statusCode = del_response.getStatusCode();
				assertEquals(200, del_statusCode);
	}
	
	@Test
	public void workoutVideoTest() throws JSONException {
		// Send request and receive response
		File file = new File("sample-mp4-file-small-demo3.mp4");
		Response post_response = RestAssured.given()
				.pathParam("id", aid)
                .multiPart("file", file, "application/json") //"multipart/form-data") 
               // .param("name", "test_file")
               // .body("test_file")
                .formParam("name", "test_file")
				.when()
				.post("/actors/{id}/workoutVideos/upload");

		// Check status code
		int statusCode = post_response.getStatusCode();
		assertEquals(200, statusCode);
		
		// Check GET actor
		Response get_actor = RestAssured.given()
				.contentType("application/json")
				.body(new LoginVO("test@iastate.edu", "test"))
				.when()
				.get("/actors/getActor");
		String returnString = get_actor.getBody().asString();
		try {
			JSONObject returnObj = new JSONObject(returnString);
			JSONArray workoutVideoArr = returnObj.getJSONArray("allWorkoutVideos");
			JSONObject workoutVideo = workoutVideoArr.getJSONObject(0);
			assertEquals("test_file", workoutVideo.get("name"));
			assertEquals("/uploads/sample-mp4-file-small-demo3.mp4", workoutVideo.get("path"));
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
		/********DELETE Test********/		
		// DELETE ALL- Send request and receive response
		Response del_response = RestAssured.given()
				.contentType("application/json")
				.pathParam("id", aid)
				.when()
				.delete("/actors/{id}/workoutVideos/all");


		// Check status code
		int del_statusCode = del_response.getStatusCode();
		assertEquals(200, del_statusCode);
	}
}

