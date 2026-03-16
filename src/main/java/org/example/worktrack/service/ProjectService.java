package org.example.worktrack.service;

import jakarta.transaction.Transactional;
import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.entities.Clients;
import org.example.worktrack.entities.Projects;
import org.example.worktrack.entities.User;
import org.example.worktrack.enums.ProjectStatus;
import org.example.worktrack.mappers.ProjectMap;
import org.example.worktrack.repository.ClientRepository;
import org.example.worktrack.repository.ProjectRepository;
import org.example.worktrack.repository.TaskRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMap projectMap;
    private final TaskRepository taskRepository;
    private final ClientRepository clientRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectMap projectMap, TaskRepository taskRepository, ClientRepository clientRepository){
        this.projectRepository = projectRepository;
        this.projectMap = projectMap;
        this.taskRepository = taskRepository;
        this.clientRepository = clientRepository;
    }

    public List<ProjectsDTO> getAllProjects() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return projectRepository.findAllByClientUserEmail(email)
                .stream()
                .map(projectMap::toDto)
                .toList();
    }

    public List<ProjectsDTO> getProjectFromClient(Long client_id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return projectRepository.findAllByClientIdAndClientUserEmail(client_id, email)
                .stream()
                .map(projectMap::toDto)
                .toList();
    }

    public ProjectsDTO getProjectById(Long id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Projects project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getClient().getUser().getEmail().equals(email)) {
            throw new RuntimeException("No authorized");
        }
        return projectMap.toDto(project);
    }

    public ProjectsDTO saveProject(ProjectsDTO dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Projects project = projectMap.toEntity(dto);
        Clients client = clientRepository.findById(project.getClient().getId()).orElseThrow(() -> new RuntimeException("not found"));
        project.setClient(client);
        if (!client.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }
        return projectMap.toDto(projectRepository.save(project));

    }

    public ProjectsDTO updateProject(Long id,ProjectsDTO dto ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Projects project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        if (!project.getClient().getUser().getEmail().equals(email)) {
            throw new RuntimeException("This isn't your project");
        }
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setStartTime(dto.getStartTime());
        project.setDeadLine(dto.getDeadLine());
        project.setTotalBudget(dto.getTotalBudget());
        project.setMoneyUsed(dto.getMoneyUsed());
        project.setStatus(dto.getStatus());
        projectRepository.save(project);
        return projectMap.toDto(project);
    }

    public void deleteProject(Long id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Projects project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        if (!project.getClient().getUser().getEmail().equals(email)) {
            throw new RuntimeException("unauthorized");
        }
        projectRepository.delete(project);
    }

}
