package it.epicode.u5w3d1.repository;

import it.epicode.u5w3d1.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);
}
