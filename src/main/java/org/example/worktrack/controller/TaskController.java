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

    @GetMapping("/list")
    public String listAllTasks(Model model) {
        List<TasksDTO> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/new/{id}")
    public String showCreateForm(@PathVariable Long id, Model model) {
        TasksDTO taskDTO = new TasksDTO();
        ProjectsDTO projectDTO = projectService.getProjectById(id);
        taskDTO.setProjectId(projectDTO.getId());

        model.addAttribute("task", new TasksDTO());
        model.addAttribute("projectId", id);
        return "tasks/create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TasksDTO taskDTO = taskService.getTaskById(id);
        model.addAttribute("task", taskDTO);
        model.addAttribute("projectId", taskDTO.getProjectId());
        return "tasks/edit";
    }

    @PostMapping("/save/{id}")
    public String saveTask(@ModelAttribute("task") TasksDTO tasksDTO, @PathVariable Long id) {
        tasksDTO.setId(null);
        tasksDTO.setProjectId(id);
        taskService.saveTask(tasksDTO);
        return "redirect:/projects/details/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, @RequestParam Long projectId) {
        taskService.deleteTask(id);
        return "redirect:task/list" + projectId;
    }
}