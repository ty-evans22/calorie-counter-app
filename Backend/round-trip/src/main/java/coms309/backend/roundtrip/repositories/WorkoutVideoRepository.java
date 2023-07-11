package coms309.backend.roundtrip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import coms309.backend.roundtrip.models.WorkoutVideo;

public interface WorkoutVideoRepository extends JpaRepository<WorkoutVideo,Long>{
    WorkoutVideo findById(int id);
    @Transactional
    void deleteById(int id);
}