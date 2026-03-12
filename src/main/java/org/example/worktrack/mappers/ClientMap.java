package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.entities.Clients;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMap {
    ClientDTO toDto(Clients clients);
    Clients toEntity(ClientDTO clientDTO);
}
