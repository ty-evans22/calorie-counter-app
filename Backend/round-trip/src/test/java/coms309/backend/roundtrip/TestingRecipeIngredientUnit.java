package coms309.backend.roundtrip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import coms309.backend.roundtrip.models.RecipeIngredients;

@RunWith(SpringRunner.class)
public class TestingRecipeIngredientUnit {

    @Test
    public void testRecipeIngredient() {
        // create an instance of the recipe ingredient
        RecipeIngredients recipeIngredient = new RecipeIngredients(5);

        // check if it works by calling its methods
        assertEquals(5, recipeIngredient.getAmount());
        assertNull(recipeIngredient.getIngredient());
        assertNull(recipeIngredient.getRecipe());
    }
}
