package org.example.worktrack.repository;

import org.example.worktrack.entities.Projects;
import org.example.worktrack.entities.Tasks;  // <-- this is the entity
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {
    @EntityGraph(attributePaths = "project",type = EntityGraph.EntityGraphType.FETCH)
    List<Tasks> findAllByProjectClientUserEmail(String email);
}
