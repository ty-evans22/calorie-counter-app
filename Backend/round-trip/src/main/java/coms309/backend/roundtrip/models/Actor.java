package coms309.backend.roundtrip.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Primary key of the Actor table",name="id",required=true,value="test id")
    private @Getter @Setter int id;

    //names
    @Column(nullable = false)
    @ApiModelProperty(notes = "Actor's first name",name="firstName",required=true,value="test firstName")
    private @Getter @Setter String firstName;
    @Column(nullable = true)
    @ApiModelProperty(notes = "Actor's middle name (can be null)",name="middleName",required=false,value="test middleName")
    private @Getter @Setter String middleName;
    @Column(nullable = false)
    @ApiModelProperty(notes = "Actor's last name",name="lastName",required=true,value="test lastName")
    private @Getter @Setter String lastName;
    //userName
    @Column(nullable = false)
    @ApiModelProperty(notes = "Actor's username",name="userName",required=true,value="test userName")
    private @Getter @Setter String userName;
    //email
    @Column(nullable = false, unique=true)
    @ApiModelProperty(notes = "Actor's email",name="email",required=true,value="test email")
    private @Getter @Setter String email;
    //password
    @Column(nullable = false)
    @ApiModelProperty(notes = "Actor's password",name="password",required=true,value="test password")
    private @Getter @Setter String password; //maybe will use char[]
    //age
    @Column(nullable = false)
    @ApiModelProperty(notes = "Actor's age",name="age",required=true,value="test age")
    private @Getter @Setter int age;
    //physical details
    @Column(nullable = false)
    @ApiModelProperty(notes = "Actor's height in inches",name="heightInches",required=true,value="test heightInches")
    private @Getter @Setter int heightInches;
    @Column(nullable = false)
    @ApiModelProperty(notes = "Actor's weight in pounds",name="weightLbs",required=true,value="test weightLbs")
    private @Getter @Setter double weightLbs;
    //role
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Actor's role - user or trainer or admin",name="role",required=true,value="test role")
    private @Getter @Setter Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "Actor's workout list (user)",name="workouts",required=true,value="test workouts")
    private List<Workout> workouts = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "Actor's workout video list (trainer)",name="workoutVideos",required=true,value="test workoutVideos")
    private List<WorkoutVideo> workoutVideos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "Actor's recipe list",name="recipes",required=true,value="test recipes")
    private List<Recipe> recipes = new ArrayList<>();

    private @Getter @Setter double caloriesIntake;

    private @Getter @Setter double caloriesBurned;

    private @Getter @Setter double calorieDifference;

    //Constructor
    public Actor(String firstName, String middleName, String lastName, String userName, String email, String password,
    		 int age, int heightInches, double weightLbs, Role role)
    {
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.userName=userName;
        this.email=email;
        this.password=password;
        this.age=age;
        this.heightInches=heightInches;
        this.weightLbs=weightLbs;
        this.role=role;
    } 
    
    /****************************FUNCTIONALITY SPECIFIC METHODS****************************/

    /******RECIPE METHODS*******/
    public List<Recipe> getAllRecipes() { return recipes; }

    public void addRecipe(Recipe recipe) { this.recipes.add(recipe); }

    public void removeRecipe(Recipe recipe) { this.recipes.remove(recipe); }

    public void updateRecipe(Recipe old, Recipe updated) {
        this.recipes.set(this.recipes.indexOf(old), updated);
    }

    public Recipe getRecipe(int i) {
        if (i > this.recipes.size() || i <= 0) throw new IllegalArgumentException("Invalid index");
        else {
            return this.recipes.get(i - 1);
        }
    }
    
    /******WORKOUT METHODS******/
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
        if(i > this.workouts.size() || i <= 0) throw new IllegalArgumentException("Invalid index");
        else
        {
            return this.workouts.get(i-1);
        }
    }
    
    /******WORKOUT VIDEO METHODS******/
    public List<WorkoutVideo> getAllWorkoutVideos() {
        return new ArrayList<>(workoutVideos);
    }

    public void addWorkoutVideo(WorkoutVideo workoutVideo)
    {
        this.workoutVideos.add(workoutVideo);
    }

    public void removeWorkoutVideo(WorkoutVideo workoutVideo)
    {
        this.workoutVideos.remove(workoutVideo);
    }
    
    public WorkoutVideo getWorkoutVideo(int i)
    {
        if(i > this.workoutVideos.size() || i <= 0) throw new IllegalArgumentException("Invalid index");
        else
        {
            return this.workoutVideos.get(i-1);
        }
    }
}
