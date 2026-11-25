package tn.esprit.tpautoloc.service.factory;

import org.springframework.stereotype.Component;
import tn.esprit.tpautoloc.domain.Reservation;
import tn.esprit.tpautoloc.service.strategy.ITarificationStrategy;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Sélectionne dynamiquement l'ITarificationStrategy adaptée à une réservation.
 * Toutes les implémentations Spring de ITarificationStrategy sont injectées
 * automatiquement dans la Map<code, strategy>.
 */
@Component
public class TarificationStrategyFactory {

    private final Map<String, ITarificationStrategy> strategies;

    public TarificationStrategyFactory(List<ITarificationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        ITarificationStrategy::getCode,
                        s -> s
                ));
    }

    public ITarificationStrategy resoudre(Reservation reservation) {
        long jours = ChronoUnit.DAYS.between(
                reservation.getDateDebut(), reservation.getDateFin());
        jours = Math.max(jours, 1);

        if (jours >= 7) {
            return strategies.get("LONGUE_DUREE");
        }
        if (jours <= 3 && estWeekend(reservation)) {
            return strategies.get("WEEKEND");
        }
        return strategies.get("STANDARD");
    }

    private boolean estWeekend(Reservation reservation) {
        DayOfWeek debut = reservation.getDateDebut().getDayOfWeek();
        return debut == DayOfWeek.FRIDAY
                || debut == DayOfWeek.SATURDAY
                || debut == DayOfWeek.SUNDAY;
    }
}