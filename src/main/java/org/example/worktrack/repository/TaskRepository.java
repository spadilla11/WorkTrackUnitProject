package org.example.worktrack.repository;

import org.example.worktrack.entities.Tasks;  // <-- this is the entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
