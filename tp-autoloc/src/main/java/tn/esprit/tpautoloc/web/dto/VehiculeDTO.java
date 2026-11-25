package tn.esprit.tpautoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tpautoloc.domain.enums.CategorieVehicule;
import tn.esprit.tpautoloc.domain.enums.StatutVehicule;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO {
    private Long id;
    @NotBlank(message = "L'immatriculation est obligatoire")
    private String immatriculation;
    @NotBlank(message = "La marque est obligatoire")
    private String marque;
    private String modele;
    @NotNull(message = "La catégorie est obligatoire")
    private CategorieVehicule categorie;
    @NotNull(message = "Le tarif est obligatoire")
    @Positive(message = "Le tarif doit être positif")
    private BigDecimal tarifJournalier;
    private StatutVehicule statut;
    @NotNull(message = "L'agence est obligatoire")
    private Long agenceId;
}