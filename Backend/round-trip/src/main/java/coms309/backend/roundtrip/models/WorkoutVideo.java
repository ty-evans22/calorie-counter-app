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
@Table(name="workout_video")
public class WorkoutVideo {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Primary key of the WorkoutVideo table",name="id",required=true,value="test id")
    private @Getter @Setter int id;
	
	//video name
	@Column
	@ApiModelProperty(notes = "Video name",name="name",required=true,value="test name")
	private @Getter @Setter String name;
	//video path
	@Column (unique=true)
	@ApiModelProperty(notes = "Video path in the filesystem",name="path",required=true,value="test path")
	private @Getter @Setter String path;

	@ManyToOne
    @JoinColumn(name = "actor_id")
    @JsonIgnore
    @ApiModelProperty(notes = "Actor associated with the video",name="actor",required=true,value="test actor")
    private @Getter @Setter Actor actor;
	
	public WorkoutVideo(String name, String path)
	{
		this.name=name;
		this.path=path;
	}
}
