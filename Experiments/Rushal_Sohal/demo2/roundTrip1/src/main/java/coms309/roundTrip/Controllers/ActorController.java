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
public class ActorController {

    @Autowired
    ActorRepository actorRepository;
    
    @Autowired
    WorkoutRepository workoutRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    //GET
    @GetMapping(path = "/actors")
    List<Actor> getAllUsers(){
        return actorRepository.findAll();
    }

    @GetMapping(path = "/actors/{id}")
    Actor getUserById( @PathVariable int id){
        return actorRepository.findById(id);
    }
    
    //POST
    @PostMapping(path = "/actors/add")
    String createUser(@RequestBody Actor actor){
        if (actor == null)
            return failure;
        actorRepository.save(actor);
        return success;
    }

    //PUT
    @PutMapping("/actors/{id}")
    Actor updateUser(@PathVariable int id, @RequestBody Actor request){
        Actor actor = actorRepository.findById(id);
        if(actor == null)
            return null;
        request.setId(id);
        actorRepository.save(request);
        return actorRepository.findById(id);
    }   

    //DELETE
    @DeleteMapping(path = "/actors/{id}")
    String deleteUser(@PathVariable int id){
    	Actor actor = actorRepository.findById(id);
    	//delete all workouts first
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
    	//delete actor now
        actorRepository.deleteById(id);
        return success;
    }    
}
