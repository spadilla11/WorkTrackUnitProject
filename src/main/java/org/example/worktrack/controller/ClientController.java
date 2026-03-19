package org.example.worktrack.controller;

import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.service.ClientService;
import org.example.worktrack.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ProjectService projectService;

    public ClientController(ClientService clientService, ProjectService projectService) {

        this.clientService = clientService;
        this.projectService = projectService;
    }


    @GetMapping("/CLprojects/{id}")
    public String ClientProjects(@PathVariable Long id, Model model) {
        ClientDTO client = clientService.getClientById(id);
        client.setProjects(projectService.getProjectFromClient(id));

        model.addAttribute("client", client);
        addGlobalStats(model);

        return "clients/client-projects";
    }

    @GetMapping("/home")
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        addGlobalStats(model);
        return "clients/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("client", new ClientDTO());
        return "clients/create";
    }

    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") ClientDTO clientDTO) {
        clientService.addClient(clientDTO);
        return "redirect:/clients/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients/home";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ClientDTO clientDTO = clientService.getClientById(id);
        model.addAttribute("client", clientDTO);
        return "clients/edit";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute("client") ClientDTO clientDTO) {
        clientDTO.setId(id);
        clientService.addClient(clientDTO);
        return "redirect:/clients/home";
    }

    private void addGlobalStats(Model model) {
        List<ClientDTO> clients = clientService.getAllClients();
        int projectsCount = 0;
        int pendingTask = 0;


        if (clients != null) {
            for (ClientDTO c : clients) {
                List<ProjectsDTO> projects = projectService.getProjectFromClient(c.getId());
                if (projects != null) {
                    projectsCount += projects.size();
                }
                for (ProjectsDTO project : projects) {
                    for (TasksDTO task : project.getTasks()) {
                        if (task.isCompleted()) {
                            continue;
                        } else {
                            pendingTask++;
                        }
                    }
                }
            }
            model.addAttribute("totalClients", clients.size());
        } else {
            model.addAttribute("totalClients", 0);
        }


        model.addAttribute("totalProjects", projectsCount);
        model.addAttribute("totalTasks", pendingTask);
    }
}
