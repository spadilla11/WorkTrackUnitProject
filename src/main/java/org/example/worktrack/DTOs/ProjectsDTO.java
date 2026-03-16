package org.example.worktrack.DTOs;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.worktrack.DTOs.ClientDTO;
import org.example.worktrack.enums.ProjectStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate startTime;
    private LocalDate deadLine;
    private Float totalBudget;
    private Float moneyUsed;
//    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @JsonIgnore
    private ClientDTO client;

    private Set<TasksDTO> tasks = new HashSet<>();
}
