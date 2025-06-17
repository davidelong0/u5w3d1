package it.epicode.u5w3d1.service;

import it.epicode.u5w3d1.dto.DipendenteDTO;
import it.epicode.u5w3d1.exception.BadRequestException;
import it.epicode.u5w3d1.exception.NotFoundException;
import it.epicode.u5w3d1.model.Dipendente;
import it.epicode.u5w3d1.repository.DipendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DipendenteService implements UserDetailsService {

    private final DipendenteRepository repo;
    private final PasswordEncoder passwordEncoder;

    public Dipendente crea(DipendenteDTO dto) {
        // Controllo email duplicata
        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Email già registrata: " + dto.getEmail());
        }

        Dipendente d = Dipendente.builder()
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        return repo.save(d);
    }

    public List<Dipendente> trovaTutti() {
        return repo.findAll();
    }

    public Dipendente trovaPerId(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
    }

    public Dipendente aggiorna(Long id, DipendenteDTO dto) {
        Dipendente d = trovaPerId(id);
        d.setNome(dto.getNome());
        d.setCognome(dto.getCognome());
        d.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            d.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return repo.save(d);
    }

    public void elimina(Long id) {
        repo.deleteById(id);
    }

    public Dipendente findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email);
    }
}



