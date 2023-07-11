package coms309.backend.roundtrip.service;

import coms309.backend.roundtrip.models.Ingredient;
import coms309.backend.roundtrip.models.Recipe;
import coms309.backend.roundtrip.models.RecipeIngredients;
import coms309.backend.roundtrip.repositories.RecipeIngredientsRepository;
import coms309.backend.roundtrip.repositories.RecipeRepository;
import coms309.backend.roundtrip.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeIngredientsService {
    @Autowired
    RecipeIngredientsRepository recipeIngredientsRepository;

    @Autowired
    RecipeRepository recipeRepository;

    /** Get all recipe ingredients **/
    public List<RecipeIngredients> getAllRecipeIngredients() { return recipeIngredientsRepository.findAll(); }

    /** Get ingredients by recipe **/
    public List<Ingredient> getIngredientsByRecipe(Recipe recipe) {
        List<RecipeIngredients> recipeIngredients = recipeIngredientsRepository.findByRecipe(recipe);
        List<Ingredient> ingredients = new ArrayList<>();
        for (RecipeIngredients ri : recipeIngredients)
            ingredients.add(ri.getIngredient());
        return ingredients;
    }

    /** Add a single ingredient to a recipe **/
    public Message createRecipeIngredient(RecipeIngredients recipeIngredient) {
        if (recipeIngredient == null)
            return new Message("Failure. Cannot add null RecipeIngredients object.", 1);
        recipeIngredientsRepository.save(recipeIngredient);
        recalculateRecipeCals(recipeIngredient.getRecipe());
        return new Message("Success", 0);
    }

    /** Update recipe ingredient by object **/
    public RecipeIngredients updateRecipeIngredient(int id, RecipeIngredients updated) {
        RecipeIngredients r = recipeIngredientsRepository.findById(id);
        if (r == null)
            return null;
        updated.setId(id);
        recipeIngredientsRepository.save(updated);
        return recipeIngredientsRepository.findById(id);
    }

    /** Update linked recipe **/
    public RecipeIngredients updateRecipe(int id, Recipe newRecipe) {
        RecipeIngredients r = recipeIngredientsRepository.findById(id);
        if (r == null)
            return null;
        r.setRecipe(newRecipe);
        recipeIngredientsRepository.save(r);
        return recipeIngredientsRepository.findById(id);
    }

    /** Update linked ingredient **/
    public RecipeIngredients updateIngredient(int id, Ingredient newIngredient) {
        RecipeIngredients r = recipeIngredientsRepository.findById(id);
        if (r == null)
            return null;
        r.setIngredient(newIngredient);
        recipeIngredientsRepository.save(r);
        return recipeIngredientsRepository.findById(id);
    }

    /** Update ingredient amount **/
    public RecipeIngredients updateAmount(int id, int newAmount) {
        RecipeIngredients r = recipeIngredientsRepository.findById(id);
        if (r == null)
            return null;
        r.setAmount(newAmount);
        recipeIngredientsRepository.save(r);
        return recipeIngredientsRepository.findById(id);
    }

    /** Delete recipe ingredient by id **/
    public Message deleteRecipeIngredient(int id) {
        RecipeIngredients recipeIngredient = recipeIngredientsRepository.findById(id);
        if (recipeIngredient == null)
            return new Message("Failure. No RecipeIngredient with id " + id, 1);
        recipeIngredientsRepository.deleteById(id);
        return new Message("Success", 0);
    }

    // HELPER METHODS

    /** Recalculate a recipe's total calorie count **/
    public void recalculateRecipeCals(Recipe recipe) {
        int cals = 0;
        for (RecipeIngredients r : recipeIngredientsRepository.findAll()) {
            if (r.getRecipe().getId() == recipe.getId())
                cals += r.getAmount() * r.getIngredient().getCalsPer100Gram();
        }
        recipe.setTotalCal(cals);

        // save the updated version into the database
        recipeRepository.save(recipe);
    }
}
