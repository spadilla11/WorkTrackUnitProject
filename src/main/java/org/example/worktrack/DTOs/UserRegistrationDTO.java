package org.example.worktrack.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private List<ClientDTO> clients;
}
