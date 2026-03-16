package org.example.worktrack.service;

import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.entities.Projects;
import org.example.worktrack.mappers.ProjectMap;
import org.example.worktrack.repository.ProjectRepository;
import org.example.worktrack.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMap projectMap;
    private final TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectMap projectMap, TaskRepository taskRepository){
        this.projectRepository = projectRepository;
        this.projectMap = projectMap;
        this.taskRepository = taskRepository;
    }

    public List<ProjectsDTO> getAllProjects() {
        String email = org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName();

        return projectRepository.findAllByUserEmail(email).stream().map(projectMap::toDto).toList();
    }

    public ProjectsDTO getProjectById(Long id){
        Projects project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMap.toDto(project);
    }

    public ProjectsDTO saveProject(ProjectsDTO dto) {
        Projects project = projectMap.toEntity(dto);
        if (dto.getClient() != null && dto.getClient().getId() != null) {
        }
        Projects saved = projectRepository.save(project);
        return projectMap.toDto(saved);
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }

}
