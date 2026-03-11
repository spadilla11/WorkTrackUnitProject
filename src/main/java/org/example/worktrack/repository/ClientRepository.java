package org.example.worktrack.repository;

import org.example.worktrack.service.ClientService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientService, Long> {
}
