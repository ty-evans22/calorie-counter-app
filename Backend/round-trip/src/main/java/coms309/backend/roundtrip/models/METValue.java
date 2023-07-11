package coms309.backend.roundtrip.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name="MET")
public class METValue {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Primary key of the MET table",name="id",required=true,value="test id")
    private @Getter @Setter int id;

    //exercise name
	@Column (unique=true)
	@ApiModelProperty(notes = "Exercise name",name="name",required=true,value="test name")
    private @Getter @Setter String name;
    //MET value for the given exercise
	@Column
	@ApiModelProperty(notes = "Exercise MET value",name="MET",required=true,value="test MET")
    private @Getter @Setter double MET;
    
    public METValue(String name, double MET)
    {
    	this.name=name;
    	this.MET=MET;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof METValue)) return false;
        return (Integer) id != null && id == ((METValue) o).getId();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
