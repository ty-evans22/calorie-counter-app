package coms309.roundTrip.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String email;
	
	@OneToMany //(mappedBy = "actor")
	private List<Workout> workouts;
	
	//Constructor
	public Actor(String name, String email)
	{
		this.name=name;
		this.email=email;
		workouts = new ArrayList<>();
	}
	
	public Actor() {workouts = new ArrayList<>();}
	
	//Getters
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getEmail()
	{
		return email;
	}

	//Setters
	public void setId(int id)
	{
		 this.id=id;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}

	// WORKOUT METHODS
	public List<Workout> getAllWorkouts() {
        return new ArrayList<>(workouts);
    }
	
    public void addWorkout(Workout workout)
    {
        this.workouts.add(workout); 
    }
    
    public void removeWorkout(Workout workout)
    {
    	this.workouts.remove(workout); 
    }
    
    public boolean exists(Workout workout)
    {
    	return this.workouts.contains(workout);
    }
    
    public int getIndex(Workout workout)
    {
    	return this.workouts.indexOf(workout);
    }
    
    public void updateWorkout(int i, Workout workout)
    {
    	this.workouts.set(i, workout);
    }
    
    public Workout getWorkout(int i)
    {
    	if(i > this.workouts.size() || i <= 0) throw new IllegalArgumentException("Invalid workout id");
    	else
    	{
    		return this.workouts.get(i-1);
    	}
    }
}
