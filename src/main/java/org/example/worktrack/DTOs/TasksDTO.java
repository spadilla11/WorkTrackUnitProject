package org.example.worktrack.DTOs;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO {

    private Long id;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    private ProjectsDTO projects;
}
