package org.example.worktrack.service;

import jakarta.transaction.Transactional;
import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.entities.Projects;
import org.example.worktrack.entities.Tasks;
import org.example.worktrack.mappers.TaskMap;
import org.example.worktrack.repository.ProjectRepository;
import org.example.worktrack.repository.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMap taskMap;
    private final ProjectRepository projectRepo;

    public TaskService(TaskRepository taskRepository, TaskMap taskMap, ProjectRepository projectRepo) {
        this.taskRepository = taskRepository;
        this.taskMap = taskMap;
        this.projectRepo = projectRepo;
    }

    public List<TasksDTO> getAllTasks() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return taskRepository.findAllByProjectClientUserEmail(email)
                .stream()
                .map(taskMap::toDto)
                .toList();
    }

    public TasksDTO getTaskById(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getProject().getClient().getUser().getEmail().equals(email)) {
            throw new RuntimeException("Not authorized");
        }
        return taskMap.toDto(task);
    }

    public TasksDTO saveTask(TasksDTO dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Projects project = projectRepo.findById(dto.getProjectId()).orElseThrow(() -> new RuntimeException("not found"));
        if (!project.getClient().getUser().getEmail().equals(email)) {
            throw new RuntimeException("You don't own this project ");
        }
        Tasks task = taskMap.toEntity(dto);
        task.setProject(project);
        return taskMap.toDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Tasks task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        if (!task.getProject().getClient().getUser().getEmail().equals(email)) {
            throw new RuntimeException("unauthorized");
        }
        taskRepository.delete(task);
    }
}
