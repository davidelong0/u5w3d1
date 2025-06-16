package it.epicode.u5w3d1.controller;

import it.epicode.u5w3d1.dto.ViaggioDTO;
import it.epicode.u5w3d1.model.Viaggio;
import it.epicode.u5w3d1.service.ViaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@RequiredArgsConstructor
public class ViaggioController {

    private final ViaggioService service;

    @PostMapping
    public ResponseEntity<Viaggio> crea(@RequestBody ViaggioDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public List<Viaggio> tutti() {
        return service.trovaTutti();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> aggiorna(@PathVariable Long id, @RequestBody ViaggioDTO dto) {
        return ResponseEntity.ok(service.aggiorna(id, dto));
    }

    @PutMapping("/{id}/stato")
    public ResponseEntity<Viaggio> cambiaStato(@PathVariable Long id, @RequestParam Viaggio.StatoViaggio stato) {
        return ResponseEntity.ok(service.cambiaStato(id, stato));
    }

    @DeleteMapping("/{id}")
    public void elimina(@PathVariable Long id) {
        service.elimina(id);
    }
}
