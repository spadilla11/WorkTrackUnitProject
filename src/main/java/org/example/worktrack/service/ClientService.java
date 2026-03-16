package org.example.worktrack.service;

import jakarta.transaction.Transactional;
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
@Transactional
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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return clientRepo.findAllByUserEmail(email)
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

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Clients client = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        if (!client.getUser().getEmail().equals(email)) {
            throw new RuntimeException("unauthorized");
        }
        clientRepo.delete(client);
    }

    public ClientDTO updateClient(Long id, String name, String email, String company) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email_check = authentication.getName();
        Clients client = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        if (!client.getUser().getEmail().equals(email_check)){
            throw new RuntimeException("unauthorized");
        }
        client.setName(name);
        client.setCompany(company);
        client.setEmail(email);
        clientRepo.save(client);
        return clientMap.toDto(client);
    }

    public ClientDTO getClientById(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
       Clients client = clientRepo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
       if (!client.getUser().getEmail().equals(email)) {
           throw new RuntimeException("not authorized");
       }
       return clientMap.toDto(client);
    }
}
