package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.entities.Clients;
import org.example.worktrack.entities.Projects;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TaskMap.class})
public interface ProjectMap {
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName")
    ProjectsDTO toDto(Projects projects);

    @Mapping(source = "clientId", target = "client.id")
    Projects toEntity(ProjectsDTO projectsDTO);

    default Clients clientFromId(Long id) {
        if (id == null) return null;
        Clients c = new Clients();
        c.setId(id);
        return c;
    }
}
