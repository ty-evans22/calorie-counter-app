package coms309.backend.roundtrip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import coms309.backend.roundtrip.models.Workout;

public interface WorkoutRepository extends JpaRepository<Workout,Long>{
    Workout findById(int id);
    @Transactional
    void deleteById(int id);
}