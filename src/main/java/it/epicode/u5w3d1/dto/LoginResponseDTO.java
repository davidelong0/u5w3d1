package it.epicode.u5w3d1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String token;
    private String email;
    private String nome;
    private String cognome;
    private String username;
}

