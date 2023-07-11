package coms309.backend.roundtrip.controllers;

import coms309.backend.roundtrip.models.Recipe;
import coms309.backend.roundtrip.service.RecipeService;
import coms309.backend.roundtrip.vo.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "RecipeController")
@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    // GET

    @ApiOperation(value = "Get list of all Recipes in the database",
            notes = "Returns a list of all recipes in the database.",
            response = Iterable.class, tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully retrieved all recipes."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to request all recipes."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find recipes.")
    })
    @GetMapping(path = "/recipes")
    List<Recipe> getAllRecipes() { return recipeService.getAllRecipes(); }

    @ApiOperation(value = "Get a Recipe by id",
            notes = "Returns a recipe matching the specified id.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully retrieved the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to request recipes."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the recipe.")
    })
    @GetMapping(path = "/recipes/{id}")
    Recipe getRecipeById(@PathVariable @ApiParam(name = "id", value = "Recipe id", example = "1") int id) {
        return recipeService.getRecipeById(id);
    }

    // POST

    @ApiOperation(value = "Add a Recipe by object",
            notes = "Adds the provided recipe to the database.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully added the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to add recipes."),
    })
    @PostMapping(path = "/recipes/add/{aid}")
    Message createRecipe(@PathVariable int aid, @RequestBody @ApiParam(name = "recipe", value = "New recipe") Recipe recipe) {
        return recipeService.addRecipe(recipe, aid);
    }

    @ApiOperation(value = "Add a Recipe by value",
            notes = "Adds the provided recipe to the database.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully added the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to add recipes."),
    })
    @PostMapping(path = "/recipes/add/{name}/{desc}/{cals}")
    Message createUniversalRecipe(
            @PathVariable @ApiParam(name = "name", value = "Recipe name", example = "Pumpkin Pie") String name,
            @PathVariable @ApiParam(name = "desc", value = "Recipe description",
                    example = "A pie with pumpkin filling.") String desc,
            @PathVariable @ApiParam(name = "cals", value = "Number of calories", example = "400") int cals) {
        return recipeService.addUniversalRecipe(name, desc, cals);
    }

    @PostMapping(path = "/recipes/add/{name}/{desc}/{cals}/{aid}")
    Message createUserRecipe(
            @PathVariable String name,
            @PathVariable String desc,
            @PathVariable int cals,
            @PathVariable int aid) {
        return recipeService.addUserRecipe(name, desc, cals, aid);
    }

    // PUT

    @ApiOperation(value = "Updates a Recipe by object",
            notes = "Updates a recipe matching the specified id.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully updated the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to update recipes."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the recipe.")
    })
    @PutMapping(path = "/recipes/{id}")
    Recipe updateRecipe(
            @PathVariable @ApiParam(name = "id", value = "Recipe id", example = "1") int id,
            @RequestBody @ApiParam(name = "updatedRecipe", value = "New recipe") Recipe updatedRecipe) {
        return recipeService.updateRecipe(id, updatedRecipe);
    }

    @ApiOperation(value = "Updates a Recipe name",
            notes = "Updates the name of a recipe matching the specified id.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully updated the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to update recipes."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the recipe.")
    })
    @PutMapping(path = "/recipes/{id}/{name}")
    Recipe updateRecipeName(
            @PathVariable @ApiParam(name = "id", value = "Recipe id", example = "1") int id,
            @PathVariable @ApiParam(name = "name", value = "Recipe name", example = "Cheeseburger") String name) {
        return recipeService.updateRecipeName(id, name);
    }

    @ApiOperation(value = "Updates a Recipe description",
            notes = "Updates the description of a recipe matching the specified id.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully updated the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to update recipes."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the recipe.")
    })
    @PutMapping(path = "/recipes/{id}/{desc}")
    Recipe updateRecipeDescription(
            @PathVariable @ApiParam(name = "id", value = "Recipe id", example = "1") int id,
            @PathVariable @ApiParam(name = "desc", value = "Recipe description",
                    example = "A patty made from ground beef served with cheese between two buns.") String desc) {
        return recipeService.updateRecipeDescription(id, desc);
    }

    @ApiOperation(value = "Updates a Recipe calorie count",
            notes = "Updates the calorie count of a recipe matching the specified id.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully updated the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to update recipes."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the recipe.")
    })
    @PutMapping(path = "/recipes/{id}/{cals}")
    Recipe updateRecipeCals(
            @PathVariable @ApiParam(name = "id", value = "Recipe id", example = "1") int id,
            @PathVariable @ApiParam(name = "cals", value = "Recipe calorie count", example = "350") int cals) {
        return recipeService.updateRecipeCals(id, cals);
    }

    // DELETE

    @ApiOperation(value = "Deletes a Recipe by id",
            notes = "Deletes a recipe matching the specified id.",
            tags = "recipe-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully deleted the recipe."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to delete recipes."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the recipe.")
    })
    @DeleteMapping(path = "/recipes/{id}")
    Message deleteRecipe(@PathVariable @ApiParam(name = "id", value = "Recipe id", example = "1") int id) {
        return recipeService.deleteRecipe(id);
    }

    @DeleteMapping(path = "/recipes/actor/{aid}")
    Message deleteActorRecipes(@PathVariable int aid) {
        return recipeService.deleteActorRecipes(aid);
    }
}
