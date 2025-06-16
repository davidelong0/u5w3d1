package it.epicode.u5w3d1.service;

import it.epicode.u5w3d1.dto.ViaggioDTO;
import it.epicode.u5w3d1.exception.NotFoundException;
import it.epicode.u5w3d1.model.Viaggio;
import it.epicode.u5w3d1.repository.ViaggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViaggioService {

    private final ViaggioRepository repo;

    public Viaggio crea(ViaggioDTO dto) {
        Viaggio v = Viaggio.builder()
                .destinazione(dto.getDestinazione())
                .data(dto.getData())
                .stato(Viaggio.StatoViaggio.IN_PROGRAMMA)
                .build();
        return repo.save(v);
    }

    public List<Viaggio> trovaTutti() {
        return repo.findAll();
    }

    public Viaggio trovaPerId(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Viaggio non trovato"));
    }

    public Viaggio aggiorna(Long id, ViaggioDTO dto) {
        Viaggio v = trovaPerId(id);
        v.setDestinazione(dto.getDestinazione());
        v.setData(dto.getData());
        return repo.save(v);
    }

    public void elimina(Long id) {
        repo.deleteById(id);
    }

    public Viaggio cambiaStato(Long id, Viaggio.StatoViaggio stato) {
        Viaggio v = trovaPerId(id);
        v.setStato(stato);
        return repo.save(v);
    }
}
