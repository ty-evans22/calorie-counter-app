package coms309.backend.roundtrip.repositories;

import coms309.backend.roundtrip.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findById(int id);
    Actor findByEmailAndPassword(String email, String password);
    @Transactional
    void deleteById(int id);
}
