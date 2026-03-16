package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.entities.Tasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMap {
    @Mapping(target = "project", ignore = true)
    TasksDTO toDto(Tasks tasks);
    Tasks toEntity(TasksDTO tasksDTO);
}
