package tn.esprit.tpautoloc.service.factory;

import org.springframework.stereotype.Component;
import tn.esprit.tpautoloc.service.observer.EmailNotificationObserver;
import tn.esprit.tpautoloc.service.observer.IReservationObserver;
import tn.esprit.tpautoloc.service.observer.SmsNotificationObserver;

/**
 * Patron GoF : FACTORY METHOD appliqué aux observateurs de notification.
 */
@Component
public class NotificationFactory {

    private final EmailNotificationObserver emailNotificationObserver;
    private final SmsNotificationObserver smsNotificationObserver;

    public NotificationFactory(EmailNotificationObserver emailNotificationObserver,
                               SmsNotificationObserver smsNotificationObserver) {
        this.emailNotificationObserver = emailNotificationObserver;
        this.smsNotificationObserver = smsNotificationObserver;
    }

    public IReservationObserver creerObservateur(String canal) {
        return switch (canal.toUpperCase()) {
            case "EMAIL" -> emailNotificationObserver;
            case "SMS"   -> smsNotificationObserver;
            default -> throw new IllegalArgumentException(
                    "Canal de notification inconnu : " + canal);
        };
    }
}