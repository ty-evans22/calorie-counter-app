package coms309.roundTrip.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Workout {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private int duration;
	
	@ManyToOne
    @JoinColumn(name = "actor_id")
    @JsonIgnore
    private Actor actor;
	
	//Constructor
	public Workout(String name, int duration)
	{
		this.name=name;
		this.duration=duration;
	}
	
	public Workout() {}
	
	//Getters
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public Actor getActor() {
        return actor;
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
	
	public void setDuration(int duration)
	{
		this.duration=duration;
	}
	
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout )) return false;
        return (Integer)id != null && id == ((Workout) o).getId();
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
