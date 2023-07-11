package coms309.roundTrip.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import coms309.roundTrip.Model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout,Long>{
	Workout findById(int id);
	
	@Transactional
    void deleteById(int id);
}

