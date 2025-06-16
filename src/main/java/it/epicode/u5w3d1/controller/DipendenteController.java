package it.epicode.u5w3d1.controller;

import it.epicode.u5w3d1.dto.DipendenteDTO;
import it.epicode.u5w3d1.model.Dipendente;
import it.epicode.u5w3d1.service.DipendenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
@RequiredArgsConstructor
public class DipendenteController {

    private final DipendenteService service;

    @PostMapping
    public ResponseEntity<Dipendente> crea(@RequestBody DipendenteDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public List<Dipendente> tutti() {
        return service.trovaTutti();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> aggiorna(@PathVariable Long id, @RequestBody DipendenteDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @DeleteMapping("/{id}")
    public void elimina(@PathVariable Long id) {
        service.elimina(id);
    }
}
