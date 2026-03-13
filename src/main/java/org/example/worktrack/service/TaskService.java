package org.example.worktrack.service;

import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.entities.Tasks;
import org.example.worktrack.mappers.TaskMap;
import org.example.worktrack.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMap taskMap;

    public TaskService(TaskRepository taskRepository, TaskMap taskMap) {
        this.taskRepository = taskRepository;
        this.taskMap = taskMap;
    }

    public List<TasksDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMap::toDto)
                .toList();
    }

    public TasksDTO getTaskById(Long id) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMap.toDto(task);
    }

    public TasksDTO saveTask(TasksDTO dto) {
        Tasks task = taskMap.toEntity(dto);
        Tasks saved = taskRepository.save(task);
        return taskMap.toDto(saved);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
