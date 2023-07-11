package coms309.backend.roundtrip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import coms309.backend.roundtrip.models.Recipe;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class TestingRecipeUnit {

    @Test
    public void testRecipe() {
        // create an instance of the recipe
        Recipe recipe = new Recipe("test_recipe",
                "Test recipe description.");

        // check if it works by calling its methods
        assertEquals("test_recipe", recipe.getRecipeName());
        assertEquals("Test recipe description.", recipe.getDescription());
        assertEquals(0, recipe.getTotalCal());
        assertNull(recipe.getActor());
        assertTrue(recipe.getIngredientsList().isEmpty());
    }
}
