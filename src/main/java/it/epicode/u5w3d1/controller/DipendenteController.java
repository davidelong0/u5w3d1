package it.epicode.u5w3d1.controller;

import it.epicode.u5w3d1.dto.DipendenteDTO;
import it.epicode.u5w3d1.dto.DipendenteResponseDTO;
import it.epicode.u5w3d1.model.Dipendente;
import it.epicode.u5w3d1.service.DipendenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dipendenti")
@RequiredArgsConstructor
public class DipendenteController {

    private final DipendenteService service;

    @PostMapping
    public ResponseEntity<DipendenteResponseDTO> crea(@RequestBody DipendenteDTO dto) {
        Dipendente creato = service.crea(dto);
        DipendenteResponseDTO responseDTO = convertToDTO(creato);
        URI location = URI.create("/api/dipendenti/" + creato.getId());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    public List<DipendenteResponseDTO> tutti() {
        return service.trovaTutti().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DipendenteResponseDTO> aggiorna(@PathVariable Long id, @RequestBody DipendenteDTO dto) {
        Dipendente aggiornato = service.aggiorna(id, dto);
        return ResponseEntity.ok(convertToDTO(aggiornato));
    }

    @DeleteMapping("/{id}")
    public void elimina(@PathVariable Long id) {
        service.elimina(id);
    }

    private DipendenteResponseDTO convertToDTO(Dipendente d) {
        return DipendenteResponseDTO.builder()
                .id(d.getId())
                .nome(d.getNome())
                .cognome(d.getCognome())
                .email(d.getEmail())
                .immagineProfiloUrl(d.getImmagineProfiloUrl())
                .build();
    }
}


