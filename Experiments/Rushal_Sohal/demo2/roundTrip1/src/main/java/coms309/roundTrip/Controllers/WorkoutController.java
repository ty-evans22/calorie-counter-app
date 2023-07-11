package coms309.roundTrip.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coms309.roundTrip.Model.Actor;
import coms309.roundTrip.Model.Workout;
import coms309.roundTrip.Repository.ActorRepository;
import coms309.roundTrip.Repository.WorkoutRepository;

@RestController
public class WorkoutController {

	@Autowired
	ActorRepository actorRepository;
	
    @Autowired
    WorkoutRepository workoutRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    
    //GET
    @GetMapping(path="/actors/{id}/workouts/all")
    List<Workout> getAllWorkouts(@PathVariable int id){
    	Actor actor = actorRepository.findById(id);
    	return actor.getAllWorkouts();
    }

    //get from index value in the list
    @GetMapping(path="/actors/{aid}/workouts/{wid}")
    Workout getWorkout(@PathVariable int aid, @PathVariable int wid){
    	return actorRepository.findById(aid).getWorkout(wid);
    	
    	//get from workout id
//    	Workout workout = workoutRepository.findById(wid);
//    	Actor actor = actorRepository.findById(aid);
//    	if(actor.exists(workout)) return workout;
//    	throw new IllegalArgumentException("Invalid workout id");
    }
    
    //get all workouts in general
    @GetMapping(path = "/workouts")
    	List<Workout> getAllWorkouts(){
    	return workoutRepository.findAll();
    }
    
    //POST    
    @PostMapping(path = "/actors/{id}/workouts/add")
    String createWorkout(@PathVariable int id, @RequestBody Workout workout){
        if (workout == null)
            return failure;
        Actor actor = actorRepository.findById(id);
        actor.addWorkout(workout);
        workout.setActor(actor);
        workoutRepository.save(workout);
        return success;
    }

    //PUT    
    @PutMapping("/actors/{aid}/workouts/{wid}")
    Workout updateWorkout(@PathVariable int aid, @PathVariable int wid, @RequestBody Workout request){
    	Actor actor = actorRepository.findById(aid);
        Workout workout = workoutRepository.findById(wid);
        int i = actor.getIndex(workout);
        if(workout == null)
            return null;
        request.setId(wid);
        actor.updateWorkout(i, request);
        request.setActor(actor);
        workoutRepository.save(request);
        return workoutRepository.findById(wid);
    } 

    //DELETE
    @DeleteMapping(path = "/actors/{aid}/workouts/{wid}")
    String deleteWorkout(@PathVariable int aid, @PathVariable int wid){
    	//delete from index value
    	Actor actor = actorRepository.findById(aid);
    	Workout workout = actor.getWorkout(wid);
    	int id = workout.getId();
        actor.removeWorkout(workout);
        workout.setActor(null);
        workoutRepository.deleteById(id);
        return success;
    }
    
    @DeleteMapping(path = "/actors/{aid}/workouts/all")
    String deleteAllWorkout(@PathVariable int aid){
    	Actor actor = actorRepository.findById(aid);
    	List<Workout> workouts = actor.getAllWorkouts();
    	Workout w;
    	for(int i=0; i<workouts.size(); i++)
    	{
    		w = workouts.get(i);
    		int wid = w.getId();
    		actor.removeWorkout(w);
            w.setActor(null);
    		workoutRepository.deleteById(wid);
    	}
        return success;
    }      
}

