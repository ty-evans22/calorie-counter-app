package coms309.backend.roundtrip.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.METValue;
import coms309.backend.roundtrip.models.Workout;
import coms309.backend.roundtrip.repositories.ActorRepository;
import coms309.backend.roundtrip.repositories.METValueRepository;
import coms309.backend.roundtrip.repositories.WorkoutRepository;
import coms309.backend.roundtrip.vo.Message;

@Service
public class WorkoutService {
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	@Autowired
    ActorRepository actorRepository;
	
    @Autowired
    METValueRepository METrepo;
	
	/***Upload Workout***/
	public Message addWorkout(Workout workout, int aid)
	{
		if (workout == null)
            return new Message("Failed. Provided workout is null.", 1);
        Actor actor = actorRepository.findById(aid);
        if (actor == null)
            return new Message("Failed. Actor at id = " + aid + " is null.", 1);
        actor.addWorkout(workout);
        workout.setActor(actor);
        //Calculate calories burned
        METValue met = METrepo.findByName(workout.getName());
        double weightKg = actor.getWeightLbs() / 2.2;
        double calories = (met.getMET()*weightKg*3.5)/200; //calories burned per minute
        calories *= workout.getDuration();
        calories = Math.floor(calories * 100) / 100;
        workout.setCaloriesBurned(calories);
        workoutRepository.save(workout);
        return new Message("Success", 0);
	}
	
	/***Get All Workouts ***/
	public List<Workout> getAllWorkouts(int aid)
	{
		Actor actor = actorRepository.findById(aid);
		return actor.getAllWorkouts();
	}
	
	/***Get Workout ***/
	public Workout getWorkout(int aid, int index) 
	{
		return actorRepository.findById(aid).getWorkout(index);
	}
	
	/***Update Workout***/
	public Message updateWorkout(Workout request, int aid, int index)
	{
		Actor actor = actorRepository.findById(aid);
        Workout workout = actor.getWorkout(index);
        if(workout == null)
            return new Message("Failed", 1);
        actor.updateWorkout(index-1, request);
        request.setActor(actor);
        workoutRepository.save(request);
        //Calculate calories burned
        METValue met = METrepo.findByName(request.getName());
        double weightKg = actor.getWeightLbs() / 2.2;
        double calories = (met.getMET()*weightKg*3.5)/200;
        calories *= request.getDuration();
        calories = Math.floor(calories * 100) / 100;
        request.setCaloriesBurned(calories);
        workoutRepository.deleteById(workout.getId());
        return new Message("Update Successful", 1);
	}
	
	/***Delete Workout***/
	public Message deleteWorkout(int aid, int index)
	{
		Actor actor = actorRepository.findById(aid);
        Workout workout = actor.getWorkout(index);
        int id = workout.getId();
        actor.removeWorkout(workout);
        workoutRepository.deleteById(id);
        return new Message("Delete Successful", 0);
	}
	
	/***Delete All Workouts***/
	public Message deleteAllWorkouts(int aid)
	{
		Actor actor = actorRepository.findById(aid);
        List<Workout> workouts = actor.getAllWorkouts();
        for (Workout w : workouts) {
            actor.removeWorkout(w);
            workoutRepository.deleteById(w.getId());
        }
        return new Message("Delete Successful", 0);
	}
	
}

