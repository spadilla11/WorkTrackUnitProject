package org.example.worktrack.repository;

import org.example.worktrack.service.ProjectService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectService,Long> {
}
