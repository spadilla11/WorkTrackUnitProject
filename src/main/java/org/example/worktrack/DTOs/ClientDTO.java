package org.example.worktrack.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String company;
    private List<ProjectsDTO> projects;
    private Long userId;
}
