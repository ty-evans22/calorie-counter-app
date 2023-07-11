package coms309.backend.roundtrip.controllers;

import coms309.backend.roundtrip.models.Ingredient;
import coms309.backend.roundtrip.models.Recipe;
import coms309.backend.roundtrip.models.RecipeIngredients;
import coms309.backend.roundtrip.service.RecipeIngredientsService;
import coms309.backend.roundtrip.vo.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "RecipeIngredientsController")
@RestController
public class RecipeIngredientsController {
    @Autowired
    RecipeIngredientsService recipeIngredientsService;

    // GET

    @ApiOperation(value = "Get list of all RecipeIngredients in the database",
            notes = "Returns a list of all recipe ingredients in the database.",
            response = Iterable.class, tags = "recipe-ingredients-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully retrieved all recipe ingredients."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to request all recipe ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find recipe ingredients")
    })
    @GetMapping(path = "/recipe_ingredients")
    List<RecipeIngredients> getAllRecipeIngredients() { return recipeIngredientsService.getAllRecipeIngredients(); }

    @ApiOperation(value = "Get list of all Ingredients for a Recipe",
            notes = "Returns a list of all ingredients in a recipe.",
            response = Iterable.class, tags = "recipe-ingredients-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully retrieved recipe ingredients."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to request recipe ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find recipe ingredients")
    })
    @GetMapping(path = "/recipe_ingredients/recipe/ingredients")
    List<Ingredient> getIngredientsByRecipe(
            @RequestBody @ApiParam(name = "recipe", value = "Recipe object") Recipe recipe) {
        return recipeIngredientsService.getIngredientsByRecipe(recipe);
    }

    // POST

    @ApiOperation(value = "Add a RecipeIngredient by object",
            notes = "Adds a single ingredient to a recipe.",
            tags = "recipe-ingredients-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully added the recipe ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to add recipe ingredients."),
    })
    @PostMapping(path = "/recipe_ingredients/add")
    Message createRecipeIngredient(
            @RequestBody @ApiParam(name = "recipeIngredients", value = "RecipeIngredients object")
            RecipeIngredients recipeIngredients) {
        return recipeIngredientsService.createRecipeIngredient(recipeIngredients);
    }

    // PUT

    // update by object
    @PutMapping(path = "/recipe_ingredients/{id}")
    RecipeIngredients updateRecipeIngredient(@PathVariable int id, @RequestBody RecipeIngredients updatedRecipeIngredient) {
        return recipeIngredientsService.updateRecipeIngredient(id, updatedRecipeIngredient);
    }

    // update recipe
    @PutMapping(path = "/recipe_ingredients/recipe/{id}")
    RecipeIngredients updateRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        return recipeIngredientsService.updateRecipe(id, recipe);
    }

    // update ingredient
    @PutMapping(path = "/recipe_ingredients/ingredient/{id}")
    RecipeIngredients updateIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        return recipeIngredientsService.updateIngredient(id, ingredient);
    }

    // update amount
    @PutMapping(path = "/recipe_ingredients/{id}/{amount}")
    RecipeIngredients updateIngredientAmount(@PathVariable int id, @PathVariable int amount) {
        return recipeIngredientsService.updateAmount(id, amount);
    }

    // DELETE

    @DeleteMapping(path = "/recipe_ingredients/{id}")
    Message deleteRecipeIngredient(@PathVariable int id) {
        return recipeIngredientsService.deleteRecipeIngredient(id);
    }
}
