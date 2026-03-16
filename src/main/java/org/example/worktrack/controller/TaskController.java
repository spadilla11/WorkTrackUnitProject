package org.example.worktrack.controller;

import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.service.TaskService;
import org.example.worktrack.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping
    public String listAllTasks(Model model) {
        List<TasksDTO> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/new/{projectId}")
    public String showCreateForm(@PathVariable Long projectId, Model model) {
        TasksDTO taskDTO = new TasksDTO();
        ProjectsDTO projectDTO = projectService.getProjectById(projectId);
        taskDTO.setProject(projectDTO);

        model.addAttribute("task", taskDTO);
        model.addAttribute("projectId", projectId);
        return "tasks/create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TasksDTO taskDTO = taskService.getTaskById(id);
        model.addAttribute("task", taskDTO);
        model.addAttribute("projectId", taskDTO.getProject().getId());
        return "tasks/edit";
    }

    @PostMapping("/save")
    public String saveOrUpdateTask(@ModelAttribute("task") TasksDTO tasksDTO, @RequestParam Long projectId) {
        tasksDTO.setProject(projectService.getProjectById(projectId));
        taskService.saveTask(tasksDTO);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, @RequestParam Long projectId) {
        taskService.deleteTask(id);
        return "redirect:/projects/" + projectId;
    }
}