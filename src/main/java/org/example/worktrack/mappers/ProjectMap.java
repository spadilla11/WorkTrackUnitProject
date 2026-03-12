package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.ProjectsDTO;
import org.example.worktrack.entities.Projects;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMap {

    ProjectsDTO toDto(Projects projects);
    Projects toEntity(ProjectsDTO projectsDTO);
}
