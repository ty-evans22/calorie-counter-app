package coms309.backend.roundtrip.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Ingredient id", name = "id", example = "1")
    private @Getter @Setter int id;

    @ApiModelProperty(notes = "Ingredient name", name = "ingredientName", example = "Milk")
    private @Getter @Setter String ingredientName;

    @ApiModelProperty(notes = "Calorie count", name = "calsPer100Gram", example = "55")
    private @Getter @Setter int calsPer100Gram;

    @OneToMany(mappedBy = "ingredient")
    @JsonIgnore
    private @Getter @Setter List<RecipeIngredients> recipesList = new ArrayList<>();

    /** Default empty constructor **/
    public Ingredient() {
    }

    /** Constructor with parameters **/
    public Ingredient(String ingredientName, int calsPer100Gram) {
        this.ingredientName = ingredientName;
        this.calsPer100Gram = calsPer100Gram;
    }
}
