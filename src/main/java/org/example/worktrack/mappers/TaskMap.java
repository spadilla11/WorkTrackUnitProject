package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.entities.Projects;
import org.example.worktrack.entities.Tasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMap {

    @Mapping(source = "project.id", target = "projectId")
    TasksDTO toDto(Tasks task);

    @Mapping(source = "projectId", target = "project.id")
    Tasks toEntity(TasksDTO taskDto);

    default Projects fromId(Long id) {
        if (id == null) return null;
        Projects p = new Projects();
        p.setId(id);
        return p;
    }
}
