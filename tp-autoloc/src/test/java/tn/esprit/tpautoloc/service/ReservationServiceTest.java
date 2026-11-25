package tn.esprit.tpautoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpautoloc.domain.Client;
import tn.esprit.tpautoloc.domain.Reservation;
import tn.esprit.tpautoloc.domain.enums.StatuReservation;
import tn.esprit.tpautoloc.repository.IReservationRepository;
import tn.esprit.tpautoloc.service.observer.EmailNotificationObserver;
import tn.esprit.tpautoloc.service.observer.ReservationSubject;
import tn.esprit.tpautoloc.service.observer.SmsNotificationObserver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private EmailNotificationObserver emailObserver;
    @Mock
    private SmsNotificationObserver smsObserver;
    @Mock
    private IReservationRepository reservationRepository;

    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        ReservationSubject subject = new ReservationSubject(List.of(emailObserver, smsObserver));
        reservationService = new ReservationService(subject, reservationRepository);
    }

    @Test
    void creerReservation_doitSauvegarderEtNotifierEmailEtSms() {
        // GIVEN
        Client client = new Client();
        client.setEmail("jean@test.com");
        client.setTelephone("0600000000");

        // On simule le comportement du repository : il retourne la réservation qu'on lui donne
        when(reservationRepository.save(any(Reservation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Reservation resultat = reservationService.creerReservation(client);

        // THEN
        assertThat(resultat.getStatut()).isEqualTo(StatuReservation.CONFIRMEE);

        // Vérifie que le repository a bien été appelé
        verify(reservationRepository, times(1)).save(any(Reservation.class));

        // Vérifie que les observateurs ont été notifiés
        verify(emailObserver).onReservationConfirmee(any(Reservation.class));
        verify(smsObserver).onReservationConfirmee(any(Reservation.class));
    }

    @Test
    void annulerReservation_doitSauvegarderEtNotifierEmailEtSms() {
        // GIVEN
        Reservation reservation = new Reservation();
        reservation.setIdReservation(1L);
        reservation.setStatut(StatuReservation.CONFIRMEE);
        reservation.setClient(Client.builder().email("jean@test.com").build());

        when(reservationRepository.save(any(Reservation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        reservationService.annulerReservation(reservation);

        // THEN
        assertThat(reservation.getStatut()).isEqualTo(StatuReservation.ANNULEE);

        verify(reservationRepository, times(1)).save(any(Reservation.class));

        verify(emailObserver).onReservationAnnulee(any(Reservation.class));
        verify(smsObserver).onReservationAnnulee(any(Reservation.class));
    }
}