package tn.esprit.tpautoloc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
    private Long clientId;
    private String clientNomComplet;
    private Long vehiculeId;
    private String vehiculeImmatriculation;
    private BigDecimal tarifEstime;
}
