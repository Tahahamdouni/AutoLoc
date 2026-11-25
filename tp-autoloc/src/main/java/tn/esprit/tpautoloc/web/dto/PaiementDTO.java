package tn.esprit.tpautoloc.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tpautoloc.domain.enums.ModePaiement; // <-- L'import de l'enum

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaiementDTO {
    private Long id;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;
    private LocalDate datePaiement;
    @NotNull(message = "Le mode de paiement est obligatoire")
    private ModePaiement modePaiement; // <-- On met l'Enum au lieu de String
    @NotNull(message = "Le contrat est obligatoire")
    private Long contratId;
}