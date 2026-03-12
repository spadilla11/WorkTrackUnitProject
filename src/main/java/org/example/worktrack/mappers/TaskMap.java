package org.example.worktrack.mappers;
import org.example.worktrack.DTOs.TasksDTO;
import org.example.worktrack.entities.Tasks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMap {
    TasksDTO toDto(Tasks tasks);
    Tasks toEntity(TasksDTO tasksDTO);
}
