package coms309.backend.roundtrip.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class RecipeIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "RecipeIngredients id", name = "id", example = "1")
    private @Getter @Setter int id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @ApiModelProperty(notes = "Recipe object", name = "recipe")
    @Getter @Setter Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @ApiModelProperty(notes = "Ingredient object", name = "ingredient")
    @Getter @Setter Ingredient ingredient;

    // currently amount is the number of grams * 100 - unrealistic currently but placeholder value
    @ApiModelProperty(notes = "Ingredient amount", name = "amount", example = "2")
    private @Getter @Setter int amount; // no units yet, will implement later as separate class

    /** Default empty constructor **/
    public RecipeIngredients() {
    }

    /** Parameterized constructor **/
    public RecipeIngredients(Recipe recipe, Ingredient ingredient, int amount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    /** Parameterized constructor with null values for the recipe and ingredient **/
    public RecipeIngredients(int amount) {
        this.amount = amount;
    }
}
