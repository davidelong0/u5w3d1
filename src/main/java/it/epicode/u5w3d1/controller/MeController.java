package it.epicode.u5w3d1.controller;

import it.epicode.u5w3d1.dto.DipendenteResponseDTO;
import it.epicode.u5w3d1.model.Dipendente;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class MeController {

    @GetMapping
    public DipendenteResponseDTO getCurrentUser(@AuthenticationPrincipal Dipendente currentUser) {
        return DipendenteResponseDTO.builder()
                .id(currentUser.getId())
                .nome(currentUser.getNome())
                .cognome(currentUser.getCognome())
                .email(currentUser.getEmail())
                .immagineProfiloUrl(currentUser.getImmagineProfiloUrl())
                .build();
    }
}


