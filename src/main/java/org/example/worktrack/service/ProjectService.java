package org.example.worktrack.service;

import jakarta.persistence.*;
import lombok.*;
import org.example.worktrack.enums.ProjectStatus;
import org.example.worktrack.service.ClientService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate startTime;
    private LocalDate deadLine;
    private Float totalBudget;
    private Float moneyUsed;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientService client;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_tasks",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<TaskService> tasks = new HashSet<>();


}
