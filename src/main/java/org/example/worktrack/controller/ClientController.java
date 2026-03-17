package org.example.worktrack.controller;

import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.DTOs.ProjectsDTO;
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
        model.addAttribute("client", client);
        return "clients/client-projects";
    }

    @GetMapping("/home")
    public String listClients(Model model) {
        List<ClientDTO> clients = clientService.getAllClients();
        for (ClientDTO client: clients) {
            client.setProjects(projectService.getProjectFromClient(client.getId()));
        }

        int projectsCount = 0;
        for (ClientDTO c : clients) {
            if (c.getProjects() != null) {
                projectsCount += c.getProjects().size();
            }
        }

        model.addAttribute("totalProjects", projectsCount);
        model.addAttribute("clients", clients);
        model.addAttribute("newClient", new ClientDTO());
        model.addAttribute("totalClients", clients.size());
        model.addAttribute("totalTasks", 0);
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
}
