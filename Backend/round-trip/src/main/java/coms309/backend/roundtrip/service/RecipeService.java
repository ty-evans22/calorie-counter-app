package coms309.backend.roundtrip.service;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.Recipe;
import coms309.backend.roundtrip.models.RecipeIngredients;
import coms309.backend.roundtrip.repositories.ActorRepository;
import coms309.backend.roundtrip.repositories.RecipeIngredientsRepository;
import coms309.backend.roundtrip.repositories.RecipeRepository;
import coms309.backend.roundtrip.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    RecipeIngredientsRepository recipeIngredientsRepository;

    /** Upload recipe **/
    public Message addRecipe(Recipe recipe, int aid) {
        if (recipe == null)
            return new Message("Failed. Try again", 1);
        Actor actor = actorRepository.findById(aid);
        actor.addRecipe(recipe);
        recipe.setActor(actor);
        recipeRepository.save(recipe);
        return new Message("Success", 0);
    }

    /** Upload new universal recipe **/
    public Message addUniversalRecipe(String name, String desc, int cals) {
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipeName(name);
        newRecipe.setDescription(desc);
        newRecipe.setTotalCal(cals);
        recipeRepository.save(newRecipe);
        return new Message("Success", 0);
    }

    /** Upload new recipe to user **/
    public Message addUserRecipe(String name, String desc, int cals, int aid) {
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipeName(name);
        newRecipe.setDescription(desc);
        newRecipe.setTotalCal(cals);
        Actor actor = actorRepository.findById(aid);
        actor.addRecipe(newRecipe);
        newRecipe.setActor(actor);
        recipeRepository.save(newRecipe);
        return new Message("Success", 0);
    }

    /** Delete recipe by id **/
    public Message deleteRecipe(int id) {
        Recipe recipe = recipeRepository.findById(id);
        if (recipe == null)
            return new Message("Failure. No recipe with id " + id, 1);
        // delete all RecipeIngredients referring to this recipe
        for (RecipeIngredients ri : recipeIngredientsRepository.findByRecipe(recipe))
            recipeIngredientsRepository.delete(ri);
        // remove the recipe from its actor if it exists and then delete it
        Actor actor = recipe.getActor();
        if (actor != null)
            actor.removeRecipe(recipe);
        recipeRepository.deleteById(id);
        return new Message("Success", 0);
    }

    /** Delete all recipes linked with an actor **/
    public Message deleteActorRecipes(int aid) {
        Actor actor = actorRepository.findById(aid);
        if (actor == null)
            return new Message("Failure. No actor with id " + aid, 1);
        for (Recipe recipe : actor.getAllRecipes()) {
            // delete all RecipeIngredients referring to the recipe
            for (RecipeIngredients ri : recipe.getIngredientsList())
                recipeIngredientsRepository.deleteById(ri.getId());
            // remove all recipes from the actor and delete them
            actor.removeRecipe(recipe);
            recipeRepository.deleteById(recipe.getId());
        }
        return new Message("Success", 0);
    }

    /** Update recipe by object **/
    public Recipe updateRecipe(int id, Recipe updatedRecipe) {
        Recipe recipe = recipeRepository.findById(id);
        if (recipe == null)
            return null;
        if (recipe.getActor() != null) {
            Actor actor = recipe.getActor();
            actor.updateRecipe(recipe, updatedRecipe);
            updatedRecipe.setActor(actor);
        }
        recipeRepository.save(updatedRecipe);
        recipeRepository.deleteById(id);
        return updatedRecipe;
    }

    /** Update recipe name **/
    public Recipe updateRecipeName(int id, String newName) {
        Recipe recipe = recipeRepository.findById(id);
        if (recipe == null)
            return null;
        recipe.setRecipeName(newName);
        recipeRepository.save(recipe);
        return recipeRepository.findById(id);
    }

    /** Update recipe description **/
    public Recipe updateRecipeDescription(int id, String newDesc) {
        Recipe recipe = recipeRepository.findById(id);
        if (recipe == null)
            return null;
        recipe.setDescription(newDesc);
        recipeRepository.save(recipe);
        return recipeRepository.findById(id);
    }

    /** Update recipe cals **/
    public Recipe updateRecipeCals(int id, int newCals) {
        Recipe recipe = recipeRepository.findById(id);
        if (recipe == null)
            return null;
        recipe.setTotalCal(newCals);
        recipeRepository.save(recipe);
        return recipeRepository.findById(id);
    }

    // update actor method?

    /** Get all recipes **/
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    /** Get recipe by id **/
    public Recipe getRecipeById(int id) {
        return recipeRepository.findById(id);
    }
}
