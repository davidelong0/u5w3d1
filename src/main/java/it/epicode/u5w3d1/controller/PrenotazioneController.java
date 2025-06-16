package it.epicode.u5w3d1.controller;

import it.epicode.u5w3d1.dto.PrenotazioneDTO;
import it.epicode.u5w3d1.model.Prenotazione;
import it.epicode.u5w3d1.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService service;

    @PostMapping
    public ResponseEntity<Prenotazione> crea(@RequestBody PrenotazioneDTO dto) {
        return ResponseEntity.ok(service.crea(dto));
    }

    @GetMapping
    public List<Prenotazione> tutte() {
        return service.trovaTutti();
    }
}
