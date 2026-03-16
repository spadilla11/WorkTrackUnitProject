package org.example.worktrack.repository;

import org.example.worktrack.entities.Projects;  // <-- this is the entity
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    @EntityGraph(attributePaths = "client", type = EntityGraph.EntityGraphType.FETCH)
    List<Projects> findAllByClientUserEmail(String email);
    @EntityGraph(attributePaths = "client", type = EntityGraph.EntityGraphType.FETCH)
    List<Projects> findAllByClientIdAndClientUserEmail(Long clientId, String email);
}
