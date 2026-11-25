package tn.esprit.tpautoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Génère les getters, setters, toString, equals, hashCode
@Builder // Permet de faire AgenceDTO.builder().nom("...").build()
@NoArgsConstructor // Constructeur vide (nécessaire pour Spring/Jackson)
@AllArgsConstructor // Constructeur avec tous les arguments
public class AgenceDTO {
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "La ville est obligatoire")
    private String ville;
    private String adresse;
    private String telephone;
}