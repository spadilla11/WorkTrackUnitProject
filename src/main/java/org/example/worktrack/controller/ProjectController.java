package org.example.worktrack.controller;

import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.entities.Tasks;
import org.example.worktrack.repository.ProjectRepository;
import org.example.worktrack.service.ProjectService;
import org.example.worktrack.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepo;
    private final ClientService clientService;

    public ProjectController(ProjectService projectService, ClientService clientService, ProjectRepository projectRepo) {
        this.projectService = projectService;
        this.clientService = clientService;
        this.projectRepo = projectRepo;
    }

    @GetMapping("/list")
    public String listAllProjects(Model model) {
        List<ProjectsDTO> projects = projectService.getAllProjects();
        ClientDTO client = clientService.getClientById(projects.getFirst().getClientId());
        model.addAttribute("client", client);
        model.addAttribute("projects", projects);
        model.addAttribute("newProject", new ProjectsDTO());
        return "projects/list";
    }

    @GetMapping("/client/{clientId}")
    public String listClientProjects(@PathVariable Long clientId, Model model) {

        model.addAttribute("client", clientService.getClientById(clientId));

        return "projects/list";
    }

    @GetMapping("/details/{id}")
    public String projectDetails(@PathVariable Long id, Model model) {
        Integer completedTask = 0;
        Integer progressTask = 0;

        ProjectsDTO project = projectService.getProjectById(id);
        ClientDTO client = clientService.getClientById(project.getClientId());

        for (TasksDTO task : project.getTasks()) {
            if (task.isCompleted()) {
                completedTask++;
            } else {
                progressTask++;
            }
        }
        Float remainingBudget = project.getTotalBudget() - project.getMoneyUsed();

        int progressPercent = 0;
        if (project.getTasks().size() > 0) {
            progressPercent = (int) ((double) completedTask / project.getTasks().size() * 100);
        }

        model.addAttribute("project", project);
        model.addAttribute("client", client);
        model.addAttribute("completedTask", completedTask);
        model.addAttribute("progressTask", progressTask);
        model.addAttribute("remainingBudget", remainingBudget);
        model.addAttribute("progressPercent", progressPercent);
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
        return "redirect:/projects/list";
    }

    @PostMapping("/update/{id}")
    public String updateProject(@PathVariable Long id, @RequestParam String title, @RequestParam Float totalBudget, @RequestParam String description) {

        ProjectsDTO project = projectService.getProjectById(id);

        project.setTitle(title);
        project.setTotalBudget(totalBudget);
        project.setDescription(description);

        projectService.saveProject(project);

        return "redirect:/projects/details/" + id;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("clients", clientService.getAllClients());
        return "projects/edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectRepo.delete(projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Could not be found")));
        return "redirect:/projects/list";
    }

    @PostMapping("/{id}/add-expense")
    @ResponseBody
    public Map<String, Object> addExpense(@PathVariable Long id, @RequestParam Float amount) {
        ProjectsDTO project = projectService.getProjectById(id);

        float newTotalSpent = (project.getMoneyUsed() != null ? project.getMoneyUsed() : 0) + amount;
        project.setMoneyUsed(newTotalSpent);
        projectService.saveProject(project);

        Map<String, Object> response = new HashMap<>();
        response.put("spent", newTotalSpent);
        response.put("remaining", project.getTotalBudget() - newTotalSpent);
        return response;
    }
}
