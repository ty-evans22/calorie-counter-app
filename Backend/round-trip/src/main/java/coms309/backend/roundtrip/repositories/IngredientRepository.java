package coms309.backend.roundtrip.repositories;

import coms309.backend.roundtrip.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findById(int id);

    @Transactional
    void deleteById(int id);
}
