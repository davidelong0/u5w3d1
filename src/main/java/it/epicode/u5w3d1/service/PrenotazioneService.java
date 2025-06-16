package it.epicode.u5w3d1.service;

import it.epicode.u5w3d1.dto.PrenotazioneDTO;
import it.epicode.u5w3d1.exception.BadRequestException;
import it.epicode.u5w3d1.model.Prenotazione;
import it.epicode.u5w3d1.repository.PrenotazioneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository repo;
    private final DipendenteService dipendenteService;
    private final ViaggioService viaggioService;

    public Prenotazione crea(PrenotazioneDTO dto) {
        if (repo.existsByDipendenteIdAndDataRichiesta(dto.getDipendenteId(), dto.getDataRichiesta())) {
            throw new BadRequestException("Dipendente ha già una prenotazione per questa data");
        }
        var prenotazione = Prenotazione.builder()
                .dipendente(dipendenteService.trovaPerId(dto.getDipendenteId()))
                .viaggio(viaggioService.trovaPerId(dto.getViaggioId()))
                .dataRichiesta(dto.getDataRichiesta())
                .note(dto.getNote())
                .build();
        return repo.save(prenotazione);
    }

    public List<Prenotazione> trovaTutti() {
        return repo.findAll();
    }
}