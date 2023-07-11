package coms309.roundTrip.test.repository;

import coms309.roundTrip.test.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findById(int id);

    @Transactional
    void deleteById(int id);
}
