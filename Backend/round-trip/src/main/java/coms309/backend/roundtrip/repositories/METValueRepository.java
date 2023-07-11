package coms309.backend.roundtrip.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import coms309.backend.roundtrip.models.METValue;

public interface METValueRepository extends JpaRepository<METValue, Long> {
    METValue findById(int id);
    METValue findByName(String name);
    @Transactional
    void deleteById(int id);
}
