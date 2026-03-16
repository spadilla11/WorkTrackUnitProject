package org.example.worktrack.repository;

import org.example.worktrack.entities.Projects;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Projects, Long> {

    @Query("SELECT p FROM Projects p WHERE p.client.user.email = :email")
    List<Projects> findAllByUserEmail(String email);

}
