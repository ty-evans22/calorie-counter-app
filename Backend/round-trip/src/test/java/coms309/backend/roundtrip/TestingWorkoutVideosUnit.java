package coms309.backend.roundtrip;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import coms309.backend.roundtrip.models.WorkoutVideo;


@RunWith(SpringRunner.class)
public class TestingWorkoutVideosUnit {

	@Test
	public void testWorkout()  {
		// create an instance of the workout
		WorkoutVideo wvideo = new WorkoutVideo("test_video", "test_path");

		//check if it works by calling its methods
		assertEquals("test_video", wvideo.getName());
		assertEquals("test_path", wvideo.getPath());
		assertEquals(null, wvideo.getActor());
	}

}
