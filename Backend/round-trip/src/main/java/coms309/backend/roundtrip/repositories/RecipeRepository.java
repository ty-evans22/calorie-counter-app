package coms309.backend.roundtrip.repositories;

import coms309.backend.roundtrip.models.Actor;
import coms309.backend.roundtrip.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findById(int id);

    List<Recipe> findByActor(Actor actor);

    @Transactional
    void deleteById(int id);
}
