package tn.esprit.tpautoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpautoloc.domain.Agence;
import tn.esprit.tpautoloc.domain.Reservation;

public interface IAgenceRepository extends JpaRepository<Agence,Long> {
}
