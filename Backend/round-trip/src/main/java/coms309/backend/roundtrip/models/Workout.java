package coms309.backend.roundtrip.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="workout")
public class Workout {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Primary key of the Workout table",name="id",required=true,value="test id")
    private @Getter @Setter int id;
    //workout name
    @Column
    @ApiModelProperty(notes = "Workout name",name="name",required=true,value="test name")
    private @Getter @Setter String name;
    //workout duration
    @Column
    @ApiModelProperty(notes = "Workout duration",name="duration",required=true,value="test duration")
    private @Getter @Setter int duration;
    //calories burned
    @Column
    @ApiModelProperty(notes = "Calories burned in the workout",name="caloriesBurned",required=true,value="test caloriesBurned")
    private @Getter @Setter double caloriesBurned;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    @JsonIgnore
    @ApiModelProperty(notes = "Actor associated with the workout",name="actor",required=true,value="test actor")
    private @Getter @Setter Actor actor;

    // Constructor
    public Workout(String name, int duration) {
        this.name = name;
        this.duration = duration;  
        this.caloriesBurned=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        return (Integer) id != null && id == ((Workout) o).getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
