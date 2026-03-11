package org.example.worktrack.repository;

import org.example.worktrack.service.TaskService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskService, Long> {
}
