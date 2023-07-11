package coms309.backend.roundtrip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import coms309.backend.roundtrip.models.Ingredient;

@RunWith(SpringRunner.class)
public class TestingIngredientUnit {

    @Test
    public void testIngredient() {
        // create an instance of the ingredient
        Ingredient ingredient = new Ingredient("test_ingredient", 100);

        // check if it works by calling its methods
        assertEquals("test_ingredient", ingredient.getIngredientName());
        assertEquals(100, ingredient.getCalsPer100Gram());
        assertTrue(ingredient.getRecipesList().isEmpty());
    }
}
