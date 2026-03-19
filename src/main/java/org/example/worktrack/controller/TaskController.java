package org.example.worktrack.controller;
import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.entities.Tasks;
import org.example.worktrack.service.TaskService;
import org.example.worktrack.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/add")
    public String showGeneralCreateForm(Model model) {
        model.addAttribute("task", new TasksDTO());
        model.addAttribute("projects", projectService.getAllProjects());
        return "tasks/create";
    }

    @PostMapping("/save-general")
    public String saveGeneralTask(@ModelAttribute("task") TasksDTO tasksDTO) {
        taskService.saveTask(tasksDTO);
        return "redirect:/tasks/list";
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
    public String deleteTask_details(@PathVariable Long id) {
        TasksDTO task = taskService.getTaskById(id);
        Long projectId = task.getProjectId();
        taskService.deleteTask(id);
        return "redirect:/projects/details/" + projectId;
    }

    @PostMapping("/delete/task/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/toggle/{id}")
    @ResponseBody
    public Map<String, Object> toggleTask(@PathVariable Long id) {
        taskService.toggleCompletion(id);

        TasksDTO currentTask = taskService.getTaskById(id);
        ProjectsDTO project = projectService.getProjectById(currentTask.getProjectId());

        int completedCount = 0;
        int progressCount = 0;

        for (TasksDTO t : project.getTasks()) {
            if (t.isCompleted()) {
                completedCount++;
            } else {
                progressCount++;
            }
        }

        int progressPercent = 0;
        if (!project.getTasks().isEmpty()) {
            progressPercent = (int) ((double) completedCount / project.getTasks().size() * 100);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("completedCount", completedCount);
        response.put("inProgressCount", progressCount);
        response.put("progressPercent", progressPercent);

        return response;
    }
}