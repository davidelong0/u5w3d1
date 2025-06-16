package it.epicode.u5w3d1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DipendenteDTO {
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
}

