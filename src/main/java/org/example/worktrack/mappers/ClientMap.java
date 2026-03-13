package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.entities.Clients;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProjectMap.class})
public interface ClientMap {
    ClientDTO toDto(Clients clients);
    Clients toEntity(ClientDTO clientDTO);
}
