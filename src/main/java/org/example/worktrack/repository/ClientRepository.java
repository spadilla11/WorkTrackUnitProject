package org.example.worktrack.repository;

import org.example.worktrack.entities.Clients;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {

    @EntityGraph(attributePaths = "user", type = EntityGraph.EntityGraphType.FETCH)
    List<Clients> findAllByUserEmail(String email);
}
