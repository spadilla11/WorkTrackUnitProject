package org.example.worktrack.controller;

import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String listClients(Model model) {
        List<ClientDTO> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
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
        return "redirect:/clients";
    }

    @PostMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
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
        return "redirect:/clients";
    }
}
