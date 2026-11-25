package tn.esprit.tpautoloc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpautoloc.domain.Reservation;
import tn.esprit.tpautoloc.service.factory.TarificationStrategyFactory;
import tn.esprit.tpautoloc.service.strategy.ITarificationStrategy;
import tn.esprit.tpautoloc.service.strategy.TarifLongueDureeStrategy;
import tn.esprit.tpautoloc.service.strategy.TarifStandardStrategy;
import tn.esprit.tpautoloc.service.strategy.TarifWeekendStrategy;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TarificationStrategyFactoryTest {

    private TarificationStrategyFactory factory;

    @BeforeEach
    void setUp() {
        // On instancie manuellement la factory avec nos 3 stratégies
        factory = new TarificationStrategyFactory(List.of(
                new TarifStandardStrategy(),
                new TarifWeekendStrategy(),
                new TarifLongueDureeStrategy()
        ));
    }

    @Test
    void doitRetournerLongueDureePour7JoursOuPlus() {
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.of(2024, 1, 1)); // Lundi
        r.setDateFin(LocalDate.of(2024, 1, 8));   // 7 jours plus tard

        ITarificationStrategy resultat = factory.resoudre(r);

        assertThat(resultat).isInstanceOf(TarifLongueDureeStrategy.class);
        assertThat(resultat.getCode()).isEqualTo("LONGUE_DUREE");
    }

    @Test
    void doitRetournerWeekendPourCourtSejourVendredi() {
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.of(2024, 3, 8)); // Vendredi
        r.setDateFin(LocalDate.of(2024, 3, 10));  // 2 jours plus tard (Dimanche)

        ITarificationStrategy resultat = factory.resoudre(r);

        assertThat(resultat).isInstanceOf(TarifWeekendStrategy.class);
        assertThat(resultat.getCode()).isEqualTo("WEEKEND");
    }

    @Test
    void doitRetournerStandardPourMoyenneDuree() {
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.of(2024, 3, 4)); // Lundi
        r.setDateFin(LocalDate.of(2024, 3, 8));   // 4 jours plus tard

        ITarificationStrategy resultat = factory.resoudre(r);

        assertThat(resultat).isInstanceOf(TarifStandardStrategy.class);
        assertThat(resultat.getCode()).isEqualTo("STANDARD");
    }
}