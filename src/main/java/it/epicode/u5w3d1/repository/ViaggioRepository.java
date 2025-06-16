package it.epicode.u5w3d1.repository;

import it.epicode.u5w3d1.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}
