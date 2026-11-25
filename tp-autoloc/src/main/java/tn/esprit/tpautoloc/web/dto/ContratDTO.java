package tn.esprit.tpautoloc.web.dto;

import jakarta.validation.constraints.PositiveOrZero;
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
public class ContratDTO {
    private Long id;
    private LocalDate dateSignature;
    @PositiveOrZero(message = "Le montant total doit être positif ou nul")
    private BigDecimal montantTotal;
    private boolean valide;
    private Long reservationId;
}