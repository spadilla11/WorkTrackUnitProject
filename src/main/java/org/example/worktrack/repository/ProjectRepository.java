package org.example.worktrack.repository;

import org.example.worktrack.entities.Projects;  // <-- this is the entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
}
