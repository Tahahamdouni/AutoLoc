package tn.esprit.tpautoloc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpautoloc.domain.Client;
import tn.esprit.tpautoloc.domain.Reservation;
import tn.esprit.tpautoloc.domain.enums.StatuReservation;
import tn.esprit.tpautoloc.repository.IReservationRepository;
import tn.esprit.tpautoloc.service.observer.ReservationSubject;

import java.time.LocalDate;

@Slf4j
@Service
public class ReservationService {

    private final ReservationSubject reservationSubject;
    private final IReservationRepository reservationRepository;

    public ReservationService(ReservationSubject reservationSubject, IReservationRepository reservationRepository) {
        this.reservationSubject = reservationSubject;
        this.reservationRepository = reservationRepository;
    }

    /**
     * Crée une réservation et notifie tous les observateurs.
     */
    public Reservation creerReservation(Client client) {
        Reservation reservation = Reservation.builder()
                .client(client)
                .dateDebut(LocalDate.now())
                .statut(StatuReservation.CONFIRMEE)
                .build();

        // Persiste la réservation en base de données
        reservation = reservationRepository.save(reservation);
        
        log.info("Reservation #{} créée pour le client {}", 
                reservation.getIdReservation(), reservation.getClient().getEmail());

        // Déclenche le process métier via le pattern Observer
        reservationSubject.notifierConfirmation(reservation);
        return reservation;
    }

    /**
     * Annule une réservation existante et notifie tous les observateurs.
     */
    public Reservation annulerReservation(Reservation reservation) {
        reservation.setStatut(StatuReservation.ANNULEE);

        // Persiste le changement de statut
        reservation = reservationRepository.save(reservation);

        log.info("Reservation #{} annulee pour le client {}",
                reservation.getIdReservation(), reservation.getClient().getEmail());

        reservationSubject.notifierAnnulation(reservation);
        return reservation;
    }
}