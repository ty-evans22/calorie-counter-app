package coms309.roundTrip.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import coms309.roundTrip.Model.Actor;

public interface ActorRepository extends JpaRepository<Actor,Long>{
	Actor findById(int id);
	
	@Transactional
    void deleteById(int id);
}
