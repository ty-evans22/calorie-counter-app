package coms309.backend.roundtrip.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Recipe id", name = "id", example = "1")
    private @Getter @Setter int id;

    @ApiModelProperty(notes = "Recipe name", name = "recipeName", example = "Chicken Noodle Soup")
    private @Getter @Setter String recipeName;

    @ApiModelProperty(notes = "Recipe description", name = "description",
            example = "A warm beef broth filled with chicken, noodles, and veggies.")
    private @Getter @Setter String description;

    @ApiModelProperty(notes = "Recipe calorie count", name = "totalCal", example = "450")
    private @Getter @Setter int totalCal;

    @OneToMany(mappedBy = "recipe")
    @JsonIgnore
    private @Getter @Setter List<RecipeIngredients> ingredientsList = new ArrayList<>();

    // foreign key mapping recipes to users
    @ManyToOne
    @JoinColumn(name = "actor_id")
    @JsonIgnore
    private @Getter @Setter Actor actor;

    /** Default empty constructor **/
    public Recipe() {}

    /** Constructor with parameters **/
    public Recipe(String recipeName, String description, int totalCal) {
        this.recipeName = recipeName;
        this.description = description;
        this.totalCal = totalCal;
    }

    /** Constructor without specifying calories **/
    public Recipe(String recipeName, String description) {
        this.recipeName = recipeName;
        this.description = description;
        this.totalCal = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        return id == ((Recipe) o).getId();
    }
}
