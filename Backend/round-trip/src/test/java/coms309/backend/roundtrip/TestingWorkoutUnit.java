package coms309.backend.roundtrip;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import coms309.backend.roundtrip.models.Workout;

@RunWith(SpringRunner.class)
public class TestingWorkoutUnit {

	@Test
	public void testWorkout()  {
		// create an instance of the workout
		Workout workout = new Workout("test_workout", 30);

		//check if it works by calling its methods
		assertEquals("test_workout", workout.getName());
		assertEquals(30, workout.getDuration());
		assertEquals(null, workout.getActor());
		assertEquals(0, workout.getCaloriesBurned(), 0.001);
	}

}
