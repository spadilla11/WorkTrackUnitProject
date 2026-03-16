package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.entities.Clients;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProjectMap.class})
public interface ClientMap {
    @Mapping(source = "user.id", target = "userId")
    ClientDTO toDto(Clients clients);

    @Mapping(source = "userId", target = "user.id")
    Clients toEntity(ClientDTO clientDTO);
}
