package coms309.backend.roundtrip.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import coms309.backend.roundtrip.models.Workout;
import coms309.backend.roundtrip.service.WorkoutService;
import coms309.backend.roundtrip.vo.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Workout Controller", description = "REST Apis related to actor (user) workouts")
@RestController
@RequestMapping("/actors/{aid}/workouts")
public class WorkoutController {

	@Autowired 
	WorkoutService workoutService;

    //get all workouts
	@ApiOperation(value = "Get all workouts for specific actor", response = Iterable.class, tags = "workout-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @GetMapping(value="/all")
    List<Workout> getAllWorkouts(@PathVariable int aid){
        return workoutService.getAllWorkouts(aid);
    }

    //get specific workout (index from list starting with 1)
	@ApiOperation(value = "Get specific workout for specific actor", response = Workout.class, tags = "workout-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @GetMapping(value="/{index}")
    Workout getWorkout(@PathVariable int aid, @PathVariable int index){
        return workoutService.getWorkout(aid, index);
    }

    //add a workout
	@ApiOperation(value = "Add a workout for specific actor", response = Message.class, tags = "workout-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PostMapping(value = "/add")
    Message createWorkout(@PathVariable int aid, @RequestBody Workout workout){
        return workoutService.addWorkout(workout, aid);
    }

    //update a workout
	@ApiOperation(value = "Update specific workout for specific actor", response = Message.class, tags = "workout-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @PutMapping(value="/{index}")
    Message updateWorkout(@PathVariable int aid, @PathVariable int index, @RequestBody Workout request){
        return workoutService.updateWorkout(request, aid, index);
    }

    //delete a workout
	@ApiOperation(value = "Delete specific workout for specific actor", response = Message.class, tags = "workout-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @DeleteMapping(value = "/{index}")
    Message deleteWorkout(@PathVariable int aid, @PathVariable int index){
        return workoutService.deleteWorkout(aid, index);
    }

    //delete all workouts
	@ApiOperation(value = "Delete all workouts for specific actor", response = Message.class, tags = "workout-controller")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK")})
    @DeleteMapping(path = "/all")
    Message deleteAllWorkout(@PathVariable int aid){
        return workoutService.deleteAllWorkouts(aid);
    }
}

