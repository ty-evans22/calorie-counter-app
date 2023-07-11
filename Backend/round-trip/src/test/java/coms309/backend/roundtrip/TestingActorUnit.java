package coms309.backend.roundtrip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.Role;

@RunWith(SpringRunner.class)
public class TestingActorUnit {

	@Test
	public void testWorkout()  {
		// create an instance of the workout
		Actor actor = new Actor("rushal", null, "sohal", "rsohal", "rsohal@iastate.edu", "pwd", 
				21, 76, 190, Role.ADMIN);
				

		//check if it works by calling its methods
		assertEquals("rushal", actor.getFirstName());
		assertEquals(null, actor.getMiddleName());
		assertEquals("sohal", actor.getLastName());
		assertEquals("rsohal", actor.getUserName());
		assertEquals("pwd", actor.getPassword());
		assertEquals("rsohal@iastate.edu", actor.getEmail());
		assertEquals(21, actor.getAge());
		assertEquals(76, actor.getHeightInches());
		assertEquals(190, actor.getWeightLbs());
		assertEquals(Role.ADMIN, actor.getRole());
		assertTrue(actor.getAllWorkouts().isEmpty());
		assertTrue(actor.getAllWorkoutVideos().isEmpty());
	}

}
