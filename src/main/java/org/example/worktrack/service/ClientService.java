package org.example.worktrack.service;

import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.entities.Clients;
import org.example.worktrack.entities.User;
import org.example.worktrack.mappers.ClientMap;
import org.example.worktrack.repository.ClientRepository;
import org.example.worktrack.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientMap clientMap;
    private final ClientRepository clientRepo;
    private final UserRepository userRepo;

    public ClientService(ClientMap clientMap, ClientRepository clientRepo, UserRepository userRepo) {
        this.clientMap = clientMap;
        this.clientRepo = clientRepo;
        this.userRepo = userRepo;
    }


    public List<ClientDTO> getAllClients() {
        return clientRepo.findAllWithUser()
                .stream()
                .map(clientMap::toDto)
                .toList();
    }

    public ClientDTO addClient(ClientDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Clients client = clientMap.toEntity(dto);
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        client.setUser(user);
        Clients newClient = clientRepo.save(client);
        return clientMap.toDto(newClient);
    }

    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }

    public ClientDTO getClientById(Long id) {
       Clients client = clientRepo.findById(id).orElse(null);
       return clientMap.toDto(client);
    }
}
