package coms309.backend.roundtrip.repositories;

import coms309.backend.roundtrip.models.Ingredient;
import coms309.backend.roundtrip.models.Recipe;
import coms309.backend.roundtrip.models.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> {
    RecipeIngredients findById(int id);

    List<RecipeIngredients> findByRecipe(Recipe recipe);

    List<RecipeIngredients> findByIngredient(Ingredient ingredient);

    @Transactional
    void deleteById(int id);
}
