package org.example.worktrack.controller;

import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.service.ProjectService;
import org.example.worktrack.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ClientService clientService;

    public ProjectController(ProjectService projectService, ClientService clientService) {
        this.projectService = projectService;
        this.clientService = clientService;
    }

    @GetMapping
    public String listAllProjects(Model model) {
        List<ProjectsDTO> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/list";
    }

    @GetMapping("/client/{clientId}")
    public String listClientProjects(@PathVariable Long clientId, Model model) {

        model.addAttribute("client", clientService.getClientById(clientId));
        return "projects/client-projects";
    }

    @GetMapping("/{id}")
    public String projectDetails(@PathVariable Long id, Model model) {
        ProjectsDTO project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "projects/details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new ProjectsDTO());
        model.addAttribute("clients", clientService.getAllClients());
        return "projects/create";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute("project") ProjectsDTO projectsDTO) {

        projectService.saveProject(projectsDTO);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("clients", clientService.getAllClients());
        return "projects/edit";
    }
}
