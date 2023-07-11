package coms309.roundTrip.test.controller;

import coms309.roundTrip.test.model.Ingredient;
import coms309.roundTrip.test.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    private final String SUCCESS = "{\"message\":\"success\"}";
    private final String FAILURE = "{\"message\":\"failure\"}";

    @Autowired
    IngredientRepository ingredientRepository;

    // GET

    @GetMapping(path = "/ingredients")
    List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping(path = "/ingredients/{id}")
    Ingredient getIngredientById(@PathVariable int id) {
        return ingredientRepository.findById(id);
    }

    // POST

    @PostMapping(path = "/ingredients/add")
    String createIngredient(@RequestBody Ingredient ingredient) {
        if (ingredient == null)
            return FAILURE;
        ingredientRepository.save(ingredient);
        return SUCCESS;
    }

    @PostMapping(path = "/ingredients/add/{name}/{cals}")
    String createIngredient(@PathVariable String name, @PathVariable int cals) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setIngredientName(name);
        newIngredient.setCalsPer100Gram(cals);
        ingredientRepository.save(newIngredient);
        return SUCCESS;
    }

    // PUT

    @PutMapping(path = "/ingredients/{id}")
    Ingredient updateIngredient(@PathVariable int id, @RequestBody Ingredient updatedIngredient) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return null;
        updatedIngredient.setId(id);
        ingredientRepository.save(updatedIngredient);
        return ingredientRepository.findById(id);
    }

    @PutMapping(path = "/ingredients/{id}/{name}")
    Ingredient updateIngredientName(@PathVariable int id, @PathVariable String name) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return null;
        ingredient.setIngredientName(name);
        ingredientRepository.save(ingredient);
        return ingredientRepository.findById(id);
    }

    @PutMapping(path = "/ingredients/{id}/{cals}")
    Ingredient updateIngredientCals(@PathVariable int id, @PathVariable int cals) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return null;
        ingredient.setCalsPer100Gram(cals);
        ingredientRepository.save(ingredient);
        return ingredientRepository.findById(id);
    }

    // DELETE

    @DeleteMapping(path = "/ingredients/{id}")
    String deleteIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientRepository.findById(id);
        if (ingredient == null)
            return FAILURE;
        ingredientRepository.deleteById(id);
        return SUCCESS;
    }
}
