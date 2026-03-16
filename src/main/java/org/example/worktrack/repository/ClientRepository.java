package org.example.worktrack.repository;

import org.example.worktrack.entities.Clients;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
//public interface ClientRepository extends JpaRepository<Clients, Long> {
//
//    @EntityGraph(attributePaths = "user")
//    List<Clients> findAllWithUser();
//}

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {

    @EntityGraph(attributePaths = "user")
    @Query("SELECT c FROM Clients c")
    List<Clients> findAllWithUser();
}
