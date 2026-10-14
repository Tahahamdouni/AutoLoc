package tn.esprit.tpautoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpautoloc.domain.Maintenance;
import tn.esprit.tpautoloc.domain.Reservation;

public interface IMaintenanceRepository extends JpaRepository<Maintenance,Long> {
}
