package coms309.backend.roundtrip.controllers;

import coms309.backend.roundtrip.models.Ingredient;
import coms309.backend.roundtrip.repositories.IngredientRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "IngredientController", description = "REST APIs related to the Ingredient entity.")
@RestController
public class IngredientController {

    private final String SUCCESS = "{\"message\":\"success\"}";
    private final String FAILURE = "{\"message\":\"failure\"}";

    @Autowired
    IngredientRepository ingredientRepository;

    // GET

    @ApiOperation(value = "Get list of all Ingredients in the database",
                  notes = "Returns a list of all ingredients in the database.",
                  response = Iterable.class, tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully retrieved all ingredients."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to request all ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find ingredients.")
    })
    @GetMapping(path = "/ingredients")
    List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @ApiOperation(value = "Get an Ingredient by id",
                  notes = "Returns an ingredient matching the specified id.",
                  tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully retrieved the ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to request ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the ingredient.")
    })
    @GetMapping(path = "/ingredients/{id}")
    Ingredient getIngredientById(@PathVariable @ApiParam(name = "id", value = "Ingredient id", example = "1") int id) {
        return ingredientRepository.findById(id);
    }

    // POST

    @ApiOperation(value = "Add an Ingredient by object",
            notes = "Adds the provided ingredient to the database.",
            tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully added the ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to add ingredients."),
    })
    @PostMapping(path = "/ingredients/add")
    String createIngredient(
            @RequestBody @ApiParam(name = "ingredient", value = "New ingredient") Ingredient ingredient) {
        if (ingredient == null)
            return FAILURE;
        ingredientRepository.save(ingredient);
        return SUCCESS;
    }

    @ApiOperation(value = "Add an Ingredient by value",
            notes = "Adds the provided ingredient to the database.",
            tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully added the ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to add ingredients."),
    })
    @PostMapping(path = "/ingredients/add/{name}/{cals}")
    String createIngredient(
            @PathVariable @ApiParam(name = "name", value = "Ingredient name", example = "Salt") String name,
            @PathVariable @ApiParam(name = "cals", value = "Number of calories", example = "55") int cals) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setIngredientName(name);
        newIngredient.setCalsPer100Gram(cals);
        ingredientRepository.save(newIngredient);
        return SUCCESS;
    }

    // PUT

    @ApiOperation(value = "Updates an Ingredient by object",
            notes = "Updates an ingredient matching the specified id.",
            tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully updated the ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to update ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the ingredient.")
    })
    @PutMapping(path = "/ingredients/{id}")
    Ingredient updateIngredient(
            @PathVariable @ApiParam(name = "id", value = "Ingredient id", example = "1") int id,
            @RequestBody @ApiParam(name = "updatedIngredient", value = "New ingredient") Ingredient updatedIngredient) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return null;
        updatedIngredient.setId(id);
        ingredientRepository.save(updatedIngredient);
        return ingredientRepository.findById(id);
    }

    @ApiOperation(value = "Updates an Ingredient name",
            notes = "Updates the name of an ingredient matching the specified id.",
            tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully updated the ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to update ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the ingredient.")
    })
    @PutMapping(path = "/ingredients/{id}/{name}")
    Ingredient updateIngredientName(
            @PathVariable @ApiParam(name = "id", value = "Ingredient id", example = "1") int id,
            @PathVariable @ApiParam(name = "name", value = "New ingredient name", example = "Sugar") String name) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return null;
        ingredient.setIngredientName(name);
        ingredientRepository.save(ingredient);
        return ingredientRepository.findById(id);
    }

    @ApiOperation(value = "Updates an Ingredient calorie count",
            notes = "Updates the calorie count of an ingredient matching the specified id.",
            tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully updated the ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to update ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the ingredient.")
    })
    @PutMapping(path = "/ingredients/{id}/{cals}")
    Ingredient updateIngredientCals(
            @PathVariable @ApiParam(name = "id", value = "Ingredient id", example = "1") int id,
            @PathVariable @ApiParam(name = "cals", value = "New calorie count", example = "55") int cals) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return null;
        ingredient.setCalsPer100Gram(cals);
        ingredientRepository.save(ingredient);
        return ingredientRepository.findById(id);
    }

    // DELETE

    @ApiOperation(value = "Deletes an Ingredient by id",
            notes = "Deletes an ingredient matching the specified id.",
            tags = "ingredient-controller")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - Successfully deleted the ingredient."),
            @ApiResponse(code = 401, message = "Not Authorized - Not authorized to delete ingredients."),
            @ApiResponse(code = 404, message = "Not Found - Failed to find the ingredient.")
    })
    @DeleteMapping(path = "/ingredients/{id}")
    String deleteIngredient(@PathVariable @ApiParam(name = "id", value = "Ingredient id", example = "1") int id) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return FAILURE;
        ingredientRepository.deleteById(id);
        return SUCCESS;
    }
}
